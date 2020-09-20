package com.jsrdxzw.shoppingmall.service

import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.jsrdxzw.shoppingmall.common.Constants
import com.jsrdxzw.shoppingmall.entity.*
import com.jsrdxzw.shoppingmall.enums.*
import com.jsrdxzw.shoppingmall.exception.MallException
import com.jsrdxzw.shoppingmall.extension.*
import com.jsrdxzw.shoppingmall.mapper.MallOrderMapper
import com.jsrdxzw.shoppingmall.service.dto.StockDTO
import com.jsrdxzw.shoppingmall.web.bo.PageRequest
import com.jsrdxzw.shoppingmall.web.bo.SaveOrderBo
import com.jsrdxzw.shoppingmall.web.vo.*
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.Exception
import java.time.LocalDateTime

/**
 * @author  xuzhiwei
 * @date  2020/09/20
 */
@Service
class MallOrderService : ServiceImpl<MallOrderMapper, MallOrder>() {
    @Autowired
    private lateinit var mallOrderMapper: MallOrderMapper

    @Autowired
    private lateinit var mallOrderItemService: MallOrderItemService

    @Autowired
    private lateinit var shoppingCartService: MallShoppingCartService

    @Autowired
    private lateinit var mallUserAddressService: MallUserAddressService

    @Autowired
    private lateinit var mallOrderAddressService: MallOrderAddressService

    @Autowired
    private lateinit var mallMerchandiseService: MallMerchandiseService

    @Transactional(rollbackFor = [Exception::class])
    fun saveOrder(saveOrderBo: SaveOrderBo, loginMallUser: MallUserLogin): String {
        val itemsForSave = shoppingCartService.getCartItemSettle(saveOrderBo.cartItemIds, loginMallUser)
        if (itemsForSave.isNullOrEmpty()) {
            MallException.fail(ServiceResult.PARAM_ERROR)
        }
        val shoppingCartItemIds = itemsForSave.mapNotNull { it.id }
        if (shoppingCartItemIds.isNullOrEmpty()) {
            MallException.fail(ServiceResult.ORDER_GENERATE_ERROR)
        }

        val priceTotal = calculateMerchandisePrice(itemsForSave)
        val mallUserAddress = getUserAddress(saveOrderBo, loginMallUser)

        val merchandiseIds = itemsForSave.mapNotNull { it.merchandiseId }
        val mallMerchandises = mallMerchandiseService.listByIds(merchandiseIds)
        val mallMerchandisesNotSelling = mallMerchandises.filter { it.merchandiseSellStatus != Constants.SELL_STATUS_UP }
        if (mallMerchandisesNotSelling.isNotEmpty()) {
            MallException.fail(ServiceResult.ORDER_SELLING_STATUS_ERROR)
        }
        val merchandiseMap = mallMerchandises.associateBy { it.id }

        // 判断商品库存
        validateStock(itemsForSave, merchandiseMap)

        //删除购物项
        val updateWrapper = lambdaUpdateWrapper<MallShoppingCartItem>()
                .`in`(MallShoppingCartItem::id, shoppingCartItemIds)
                .set(MallShoppingCartItem::isDeleted, YesOrNo.YES.code)
        shoppingCartService.update(updateWrapper)

        val stockDTOs: List<StockDTO> = itemsForSave.map { StockDTO(it.merchandiseId!!, it.merchandiseCount!!) }
        validateUpdateSuccess {
            mallMerchandiseService.updateMerchandiseStock(stockDTOs)
        }
        val orderNumber = generateOrderNumber()
        val mallOrder = MallOrder().also {
            it.orderNo = orderNumber
            it.userId = loginMallUser.userId
            it.totalPrice = priceTotal
        }
        updateSuccessCallback({ mallOrderMapper.insert(mallOrder) }) {
            val address = mallUserAddress.beanCopy(MallOrderAddress::class.java).also {
                it.orderId = mallOrder.id
            }
            val mallOrderItems = mutableListOf<MallOrderItem>()
            for (mallShoppingCartItemVO in itemsForSave) {
                val item = MallOrderItem()
                BeanUtils.copyProperties(mallShoppingCartItemVO, item)
                item.orderId = mallOrder.id
                mallOrderItems.add(item)
            }
            validateUpdateSuccess {
                mallOrderItemService.saveBatch(mallOrderItems)
            }
            validateUpdateSuccess {
                mallOrderAddressService.save(address)
            }
            return orderNumber
        }
        MallException.fail(ServiceResult.ORDER_GENERATE_ERROR)
    }

    private fun validateStock(itemsForSave: List<MallShoppingCartItemVO>, merchandiseMap: Map<Long?, MallMerchandiseInfo>) {
        for (mallShoppingCartItemVO in itemsForSave) {
            if (!merchandiseMap.containsKey(mallShoppingCartItemVO.merchandiseId)) {
                MallException.fail(ServiceResult.SHOPPING_ITEM_ERROR)
            }
            val buyCount = mallShoppingCartItemVO.merchandiseCount ?: 0
            val stockNum = merchandiseMap[mallShoppingCartItemVO.merchandiseId]?.stockNum ?: 0
            if (buyCount > stockNum) {
                MallException.fail(ServiceResult.SHOPPING_ITEM_COUNT_ERROR)
            }
        }
    }

    private fun getUserAddress(saveOrderBo: SaveOrderBo, loginMallUser: MallUserLogin): MallUserAddressVo {
        val mallUserAddress = mallUserAddressService.getUserAddressByAddressId(saveOrderBo.addressId)
                ?: MallException.fail(ServiceResult.DATA_NOT_EXIST)
        if (mallUserAddress.userId != loginMallUser.userId) {
            MallException.fail(ServiceResult.REQUEST_FORBIDEN_ERROR)
        }
        return mallUserAddress
    }

    private fun calculateMerchandisePrice(itemsForSave: List<MallShoppingCartItemVO>): Int {
        val priceTotal = itemsForSave.fold(0) { acc, mallShoppingCartItemVo ->
            val sellPrice = mallShoppingCartItemVo.sellingPrice ?: 0
            val sellCount = mallShoppingCartItemVo.merchandiseCount ?: 0
            acc + sellPrice * sellCount
        }
        if (priceTotal < 1) {
            MallException.fail(ServiceResult.ORDER_PRICE_ERROR)
        }
        return priceTotal
    }

    fun getOrderDetail(orderNumber: String, loginMallUser: MallUserLogin): MallOrderDetailVo {
        val mallOrder = getMallOrderByOrderNumber(orderNumber)

        val orderItems = mallOrderItemService.selectByOrderNumber(mallOrder.id)
        if (orderItems.isNullOrEmpty()) {
            MallException.fail(ServiceResult.ORDER_ITEM_NULL_ERROR)
        }
        val mallOrderItemVos = orderItems.copyList(MallOrderItemVo::class.java)
        return mallOrder.beanCopy(MallOrderDetailVo::class.java).also {
            it.orderStatusString = NewBeeMallOrderStatus.getNewBeeMallOrderStatusByStatus(it.orderStatus).desc
            it.payTypeString = PayType.getPayTypeByType(it.payType).desc
            it.newBeeMallOrderItemVOS = mallOrderItemVos
        }
    }

    fun orderList(pageRequest: PageRequest<MallOrder>, status: Int?, loginMallUser: MallUserLogin): IPage<MallOrderListVo> {
        val queryWrapper = lambdaQueryWrapper<MallOrder>()
                .eq(status != null, MallOrder::orderStatus, status)
                .eq(MallOrder::userId, loginMallUser.userId)
                .orderByDesc(MallOrder::createTime)
        val page = mallOrderMapper.selectPage(pageRequest, queryWrapper)
        val mallOrderList = page.records
        var result = emptyList<MallOrderListVo>()
        if (mallOrderList.isNotEmpty()) {
            result = mallOrderList.copyList(MallOrderListVo::class.java)
            result.forEach {
                it.orderStatusString = NewBeeMallOrderStatus.getNewBeeMallOrderStatusByStatus(it.orderStatus).desc
            }

            val orderIds = mallOrderList.mapNotNull { it.id }
            if (!orderIds.isNullOrEmpty()) {
                val orderItems = mallOrderItemService.list(lambdaQueryWrapper<MallOrderItem>().`in`(MallOrderItem::orderId, orderIds))
                val itemByOrderIdMap = orderItems.groupBy { it.orderId }
                result.forEach {
                    if (itemByOrderIdMap.containsKey(it.id)) {
                        itemByOrderIdMap[it.id]?.let { itemList ->
                            it.mallOrderItemVos = itemList.copyList(MallOrderItemVo::class.java)
                        }
                    }
                }
            }
        }
        return Page<MallOrderListVo>().also {
            it.records = result
            it.size = page.size
            it.current = page.current
            it.total = page.total
        }
    }

    @Transactional(rollbackFor = [Exception::class])
    fun cancelOrder(orderNumber: String, loginMallUser: MallUserLogin) {
        val mallOrder = getMallOrderByOrderNumber(orderNumber, loginMallUser)
                .also {
                    it.orderStatus = NewBeeMallOrderStatus.ORDER_CLOSED_BY_MANUALLY.orderStatus
                    it.updateTime = LocalDateTime.now()
                }
        validateUpdateSuccess {
            mallOrderMapper.updateById(mallOrder)
        }
    }

    private fun getMallOrderByOrderNumber(orderNumber: String, loginMallUser: MallUserLogin? = null): MallOrder {
        val queryWrapper = lambdaQueryWrapper<MallOrder>()
                .eq(MallOrder::orderNo, orderNumber)
                .eq(MallOrder::isDeleted, YesOrNo.NO.code)
        val mallOrder = mallOrderMapper.selectOne(queryWrapper) ?: MallException.fail(ServiceResult.DATA_NOT_EXIST)
        if (loginMallUser != null && mallOrder.userId != loginMallUser.userId) {
            MallException.fail(ServiceResult.REQUEST_FORBIDEN_ERROR)
        }
        return mallOrder
    }

    fun finishOrder(orderNumber: String, loginMallUser: MallUserLogin) {
        val mallOrder = getMallOrderByOrderNumber(orderNumber, loginMallUser)
                .also {
                    it.orderStatus = NewBeeMallOrderStatus.ORDER_SUCCESS.orderStatus
                    it.updateTime = LocalDateTime.now()
                }
        validateUpdateSuccess {
            mallOrderMapper.updateById(mallOrder)
        }
    }

    fun paySuccess(orderNumber: String, payType: Int) {
        val mallOrder = getMallOrderByOrderNumber(orderNumber)
        if (mallOrder.orderStatus != NewBeeMallOrderStatus.ORDER_PRE_PAY.orderStatus) {
            MallException.fail(ServiceResult.PAY_SUCCESS_ERROR)
        }
        mallOrder.apply {
            orderStatus = NewBeeMallOrderStatus.ORDER_PAID.orderStatus
            this.payType = payType
            payStatus = PayStatus.PAY_SUCCESS.payStatus
            payTime = LocalDateTime.now()
            updateTime = LocalDateTime.now()
        }
        validateUpdateSuccess {
            mallOrderMapper.updateById(mallOrder)
        }
    }
}
