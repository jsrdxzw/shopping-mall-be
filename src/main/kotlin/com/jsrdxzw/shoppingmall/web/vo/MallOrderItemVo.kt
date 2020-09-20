package com.jsrdxzw.shoppingmall.web.vo

import io.swagger.annotations.ApiModelProperty

/**
 * @author  xuzhiwei
 * @date  2020/09/20
 */
data class MallOrderItemVo(
        @ApiModelProperty("商品id")
        var merchandiseId: Long? = null,

        @ApiModelProperty("商品数量")
        var merchandiseCount: Int? = null,

        @ApiModelProperty("商品名称")
        var merchandiseName: String? = null,

        @ApiModelProperty("商品图片")
        var merchandiseCoverImg: String? = null,

        @ApiModelProperty("商品价格")
        var sellingPrice: Int? = null
)
