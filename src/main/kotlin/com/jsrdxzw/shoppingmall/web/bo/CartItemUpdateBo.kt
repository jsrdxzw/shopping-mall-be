package com.jsrdxzw.shoppingmall.web.bo

import io.swagger.annotations.ApiModelProperty

/**
 * @author  xuzhiwei
 * @date  2020/09/19
 */
data class CartItemUpdateBo(
        @ApiModelProperty("购物项id")
        val cartItemId: Long,
        @ApiModelProperty("商品数量")
        val merchandiseCount: Int
)
