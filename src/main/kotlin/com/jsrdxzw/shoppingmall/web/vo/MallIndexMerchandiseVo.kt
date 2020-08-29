package com.jsrdxzw.shoppingmall.web.vo

import io.swagger.annotations.ApiModelProperty

data class MallIndexMerchandiseVo(
        @ApiModelProperty("商品id")
        var merchandiseId: Long? = null,
        @ApiModelProperty("商品名称")
        var merchandiseName: String? = null,

        @ApiModelProperty("商品简介")
        var merchandiseIntro: String? = null,

        @ApiModelProperty("商品图片地址")
        var merchandiseCoverImg: String? = null,

        @ApiModelProperty("商品价格")
        var sellingPrice: Int? = null,

        @ApiModelProperty("商品标签")
        var tag: String? = null
)
