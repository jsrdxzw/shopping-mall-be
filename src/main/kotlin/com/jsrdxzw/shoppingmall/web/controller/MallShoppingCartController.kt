package com.jsrdxzw.shoppingmall.web.controller

import com.baomidou.mybatisplus.core.metadata.IPage
import com.jsrdxzw.shoppingmall.annotation.TokenToMallUser
import com.jsrdxzw.shoppingmall.entity.MallShoppingCartItem
import com.jsrdxzw.shoppingmall.service.MallShoppingCartService
import com.jsrdxzw.shoppingmall.util.ResultData
import com.jsrdxzw.shoppingmall.web.bo.CartItemAddBo
import com.jsrdxzw.shoppingmall.web.bo.CartItemUpdateBo
import com.jsrdxzw.shoppingmall.web.bo.PageRequest
import com.jsrdxzw.shoppingmall.web.vo.MallShoppingCartItemVO
import com.jsrdxzw.shoppingmall.web.vo.MallUserLogin
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.NotEmpty

/**
 * @author  xuzhiwei
 * @date  2020/09/19
 */
@Api(tags = ["新蜂商城购物车接口"])
@RestController
@RequestMapping("/shopping_cart")
class MallShoppingCartController {
    @Autowired
    private lateinit var mallShoppingCartService: MallShoppingCartService

    @GetMapping("/page")
    @ApiOperation(value = "购物车列表(每页默认10条)", notes = "传参为页码")
    fun cartItemPageList(pageRequest: PageRequest<MallShoppingCartItem>, @TokenToMallUser mallUserLogin: MallUserLogin): ResultData<IPage<MallShoppingCartItemVO>> {
        return ResultData.success(mallShoppingCartService.getShoppingCartItems(pageRequest, mallUserLogin))
    }

    @PostMapping
    @ApiOperation(value = "添加商品到购物车接口", notes = "传参为商品id、数量")
    fun addCartItem(@RequestBody cartItemAddBo: CartItemAddBo, @TokenToMallUser mallUserLogin: MallUserLogin): ResultData<*> {
        mallShoppingCartService.addCartItem(cartItemAddBo, mallUserLogin)
        return ResultData.SUCCESS
    }

    @PutMapping
    @ApiOperation(value = "修改购物项数据", notes = "传参为购物项id、数量")
    fun updateCartItem(@RequestBody cartItemUpdateBo: CartItemUpdateBo, @TokenToMallUser mallUserLogin: MallUserLogin): ResultData<*> {
        mallShoppingCartService.updateCartItem(cartItemUpdateBo, mallUserLogin)
        return ResultData.SUCCESS
    }

    @DeleteMapping("/{shoppingCartItemId}")
    @ApiOperation(value = "删除购物项", notes = "传参为购物项id")
    fun deleteCartItem(@PathVariable("shoppingCartItemId") shoppingCartItemId: Long, @TokenToMallUser mallUserLogin: MallUserLogin): ResultData<*> {
        mallShoppingCartService.deleteCartItem(shoppingCartItemId, mallUserLogin)
        return ResultData.SUCCESS
    }

    @GetMapping("/settle")
    @ApiOperation(value = "根据购物项id数组查询购物项明细", notes = "确认订单页面使用")
    fun cartItemSettle(@Valid @NotEmpty(message = "购物车商品id数组不能为空") cartItemIds: LongArray, @TokenToMallUser mallUserLogin: MallUserLogin): ResultData<List<MallShoppingCartItemVO>> {
        return ResultData.success(mallShoppingCartService.getCartItemSettle(cartItemIds, mallUserLogin))
    }
}
