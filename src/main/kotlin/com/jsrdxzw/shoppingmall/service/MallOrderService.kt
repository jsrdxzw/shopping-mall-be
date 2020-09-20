package com.jsrdxzw.shoppingmall.service

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.jsrdxzw.shoppingmall.common.Constants
import com.jsrdxzw.shoppingmall.entity.*
import com.jsrdxzw.shoppingmall.enums.NewBeeMallOrderStatus
import com.jsrdxzw.shoppingmall.enums.PayType
import com.jsrdxzw.shoppingmall.enums.ServiceResult
import com.jsrdxzw.shoppingmall.enums.YesOrNo
import com.jsrdxzw.shoppingmall.exception.MallException
import com.jsrdxzw.shoppingmall.extension.*
import com.jsrdxzw.shoppingmall.mapper.MallOrderMapper
import com.jsrdxzw.shoppingmall.service.dto.StockDTO
import com.jsrdxzw.shoppingmall.web.bo.SaveOrderBo
import com.jsrdxzw.shoppingmall.web.vo.*
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.Exception

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

        val stockDTOs: List<StockDTO> = itemsForSave.map { StockDTO(it.merchandiseId!!, it.goodsCount!!) }
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
            val buyCount = mallShoppingCartItemVO.goodsCount ?: 0
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
            val sellCount = mallShoppingCartItemVo.goodsCount ?: 0
            acc + sellPrice * sellCount
        }
        if (priceTotal < 1) {
            MallException.fail(ServiceResult.ORDER_PRICE_ERROR)
        }
        return priceTotal
    }

    fun getOrderDetail(orderNumber: String, loginMallUser: MallUserLogin): MallOrderDetailVo {
        val queryWrapper = lambdaQueryWrapper<MallOrder>()
                .eq(MallOrder::orderNo, orderNumber)
                .eq(MallOrder::isDeleted, YesOrNo.NO.code)
        val mallOrder = mallOrderMapper.selectOne(queryWrapper) ?: MallException.fail(ServiceResult.DATA_NOT_EXIST)
        if (mallOrder.userId != loginMallUser.userId) {
            MallException.fail(ServiceResult.REQUEST_FORBIDEN_ERROR)
        }
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
}
