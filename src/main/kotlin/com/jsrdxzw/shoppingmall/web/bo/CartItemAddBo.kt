package com.jsrdxzw.shoppingmall.web.bo

import io.swagger.annotations.ApiModelProperty

data class CartItemAddBo(
        @ApiModelProperty("商品数量")
        val merchandiseCount: Int,
        @ApiModelProperty("商品id")
        val merchandiseId: Long
)
