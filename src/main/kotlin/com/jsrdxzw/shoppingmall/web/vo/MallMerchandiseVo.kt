package com.jsrdxzw.shoppingmall.web.vo

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author  xuzhiwei
 * @date  2020/08/30
 */
@ApiModel("商品返回类")
data class MallMerchandiseVo(
        @ApiModelProperty("商品id")
        var id: Long? = null,
        @ApiModelProperty("商品名称")
        var merchandiseName: String? = null,
        @ApiModelProperty("商品简介")
        var merchandiseIntro: String? = null,
        @ApiModelProperty("商品图片地址")
        var merchandiseCoverImg: String? = null,
        @ApiModelProperty("商品价格")
        var sellingPrice: Int? = null
)
