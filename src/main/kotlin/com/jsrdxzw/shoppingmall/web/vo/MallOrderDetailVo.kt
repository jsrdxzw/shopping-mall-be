package com.jsrdxzw.shoppingmall.web.vo

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
import java.util.*

data class MallOrderDetailVo(
        @ApiModelProperty("订单号")
        var orderNo: String? = null,

        @ApiModelProperty("订单价格")
        var totalPrice: Int? = null,

        @ApiModelProperty("订单支付状态码")
        var payStatus: Int? = null,

        @ApiModelProperty("订单支付方式")
        var payType: Int? = null,

        @ApiModelProperty("订单支付方式")
        var payTypeString: String? = null,

        @ApiModelProperty("订单支付实践")
        var payTime: Date? = null,

        @ApiModelProperty("订单状态码")
        var orderStatus: Int? = null,

        @ApiModelProperty("订单状态")
        var orderStatusString: String? = null,

        @ApiModelProperty("创建时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        var createTime: Date? = null,

        @ApiModelProperty("订单项列表")
        var newBeeMallOrderItemVOS: List<MallOrderItemVo>? = null
)

data class MallOrderItemVo(
        @ApiModelProperty("商品id")
        var merchandiseId: Long? = null,

        @ApiModelProperty("商品数量")
        var goodsCount: Int? = null,

        @ApiModelProperty("商品名称")
        var goodsName: String? = null,

        @ApiModelProperty("商品图片")
        var goodsCoverImg: String? = null,

        @ApiModelProperty("商品价格")
        var sellingPrice: Int? = null
)
