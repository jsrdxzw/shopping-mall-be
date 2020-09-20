package com.jsrdxzw.shoppingmall.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.jsrdxzw.shoppingmall.entity.MallShoppingCartItem
import com.jsrdxzw.shoppingmall.web.vo.MallShoppingCartItemVO
import org.apache.ibatis.annotations.Param

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jsrdxzw
 * @since 2020-08-29
 */
interface MallShoppingCartItemMapper : BaseMapper<MallShoppingCartItem> {
    /**
     * 分页查询用户的购物车items
     */
    fun getUserShoppingCartItems(page: Page<*>, @Param("userId") userId: Long): IPage<MallShoppingCartItemVO>

    /**
     * 根据购物车id查询购物车商品信息
     */
    fun selectShoppingCartItemSettle(@Param("cartItemIds") cartItemIds: LongArray, @Param("userId") userId: Long): List<MallShoppingCartItemVO>
}
