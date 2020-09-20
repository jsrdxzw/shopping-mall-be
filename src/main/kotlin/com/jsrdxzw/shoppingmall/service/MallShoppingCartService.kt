package com.jsrdxzw.shoppingmall.service

import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.jsrdxzw.shoppingmall.common.Constants
import com.jsrdxzw.shoppingmall.entity.MallShoppingCartItem
import com.jsrdxzw.shoppingmall.enums.ServiceResult
import com.jsrdxzw.shoppingmall.enums.YesOrNo
import com.jsrdxzw.shoppingmall.exception.MallException
import com.jsrdxzw.shoppingmall.extension.beanCopy
import com.jsrdxzw.shoppingmall.extension.lambdaQueryWrapper
import com.jsrdxzw.shoppingmall.extension.validateUpdateSuccess
import com.jsrdxzw.shoppingmall.mapper.MallMerchandiseInfoMapper
import com.jsrdxzw.shoppingmall.mapper.MallShoppingCartItemMapper
import com.jsrdxzw.shoppingmall.web.bo.CartItemAddBo
import com.jsrdxzw.shoppingmall.web.bo.CartItemUpdateBo
import com.jsrdxzw.shoppingmall.web.bo.PageRequest
import com.jsrdxzw.shoppingmall.web.vo.MallShoppingCartItemVO
import com.jsrdxzw.shoppingmall.web.vo.MallUserLogin
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

/**
 * @author  xuzhiwei
 * @date  2020/09/19
 */
@Service
class MallShoppingCartService : ServiceImpl<MallShoppingCartItemMapper, MallShoppingCartItem>() {
    @Autowired
    private lateinit var mallShoppingCartItemMapper: MallShoppingCartItemMapper

    @Autowired
    private lateinit var mallMerchandiseInfoMapper: MallMerchandiseInfoMapper

    fun getShoppingCartItems(pageRequest: PageRequest<MallShoppingCartItem>, mallUser: MallUserLogin): IPage<MallShoppingCartItemVO> {
        return mallShoppingCartItemMapper.getUserShoppingCartItems(pageRequest, mallUser.userId)
    }

    fun addCartItem(cartItemAddBo: CartItemAddBo, mallUserLogin: MallUserLogin) {
        validateAddShoppingCart(cartItemAddBo, mallUserLogin)
        val shoppingCartItem = cartItemAddBo.beanCopy(MallShoppingCartItem::class.java)
        shoppingCartItem.userId = mallUserLogin.userId
        validateUpdateSuccess {
            mallShoppingCartItemMapper.insert(shoppingCartItem)
        }
    }

    fun updateCartItem(cartItemUpdateBo: CartItemUpdateBo, mallUserLogin: MallUserLogin) {
        val mallShoppingCartItem = mallShoppingCartItemMapper.selectById(cartItemUpdateBo.cartItemId)

        validateUpdateShoppingCart(mallShoppingCartItem, cartItemUpdateBo, mallUserLogin)

        mallShoppingCartItem.apply {
            goodsCount = cartItemUpdateBo.merchandiseCount
            updateTime = LocalDateTime.now()
        }

        validateUpdateSuccess {
            mallShoppingCartItemMapper.updateById(mallShoppingCartItem)
        }
    }

    private fun validateAddShoppingCart(cartItemAddBo: CartItemAddBo, mallUserLogin: MallUserLogin) {
        val queryWrapper = lambdaQueryWrapper<MallShoppingCartItem>()
                .eq(MallShoppingCartItem::userId, mallUserLogin.userId)
                .eq(MallShoppingCartItem::merchandiseId, cartItemAddBo.merchandiseId)
                .eq(MallShoppingCartItem::isDeleted, YesOrNo.NO.code)
        if (mallShoppingCartItemMapper.selectList(queryWrapper).isNotEmpty()) {
            MallException.fail(ServiceResult.SHOPPING_CART_ITEM_EXIST_ERROR)
        }
        val merchandise = mallMerchandiseInfoMapper.selectById(cartItemAddBo.merchandiseId)
        if (merchandise == null) {
            MallException.fail(ServiceResult.GOODS_NOT_EXIST)
        }
        val itemTotal = mallShoppingCartItemMapper.selectCount(
                lambdaQueryWrapper<MallShoppingCartItem>()
                        .eq(MallShoppingCartItem::userId, mallUserLogin.userId)
                        .eq(MallShoppingCartItem::isDeleted, YesOrNo.NO.code)
        )
        if (cartItemAddBo.merchandiseCount < 1) {
            MallException.fail(ServiceResult.SHOPPING_CART_ITEM_NUMBER_ERROR)
        }
        if (cartItemAddBo.merchandiseCount > Constants.SHOPPING_CART_ITEM_LIMIT_NUMBER) {
            MallException.fail(ServiceResult.SHOPPING_CART_ITEM_LIMIT_NUMBER_ERROR)
        }

        if (itemTotal > Constants.SHOPPING_CART_ITEM_TOTAL_NUMBER) {
            MallException.fail(ServiceResult.SHOPPING_CART_ITEM_TOTAL_NUMBER_ERROR)
        }
    }

    private fun validateUpdateShoppingCart(mallShoppingCartItem: MallShoppingCartItem?, cartItemUpdateBo: CartItemUpdateBo, mallUserLogin: MallUserLogin) {
        if (mallShoppingCartItem == null) {
            MallException.fail(ServiceResult.DATA_NOT_EXIST)
        }
        if (mallShoppingCartItem.userId != mallUserLogin.userId) {
            MallException.fail(ServiceResult.REQUEST_FORBIDEN_ERROR)
        }
        //超出单个商品的最大数量
        if (cartItemUpdateBo.merchandiseCount > Constants.SHOPPING_CART_ITEM_LIMIT_NUMBER) {
            MallException.fail(ServiceResult.SHOPPING_CART_ITEM_LIMIT_NUMBER_ERROR)
        }
    }

    fun deleteCartItem(shoppingCartItemId: Long, mallUserLogin: MallUserLogin) {
        val mallShoppingCartItem = mallShoppingCartItemMapper.selectById(shoppingCartItemId)
        if (mallShoppingCartItem.userId != mallUserLogin.userId) {
            MallException.fail(ServiceResult.REQUEST_FORBIDEN_ERROR)
        }
        mallShoppingCartItem.isDeleted = YesOrNo.YES.code
        validateUpdateSuccess {
            mallShoppingCartItemMapper.updateById(mallShoppingCartItem)
        }
    }

    fun getCartItemSettle(cartItemIds: LongArray, mallUserLogin: MallUserLogin): List<MallShoppingCartItemVO> {
        if (cartItemIds.isEmpty()) {
            MallException.fail(ServiceResult.PARAM_ERROR)
        }
        val mallShoppingCartItems = mallShoppingCartItemMapper.selectShoppingCartItemSettle(cartItemIds, mallUserLogin.userId)
        if (mallShoppingCartItems.isNullOrEmpty()) {
            MallException.fail(ServiceResult.SHOPPING_ITEM_EMPTY)
        }
        if (mallShoppingCartItems.size != cartItemIds.size) {
            MallException.fail(ServiceResult.PARAM_ERROR)
        }
        return mallShoppingCartItems
    }
}
