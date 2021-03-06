package com.jsrdxzw.shoppingmall.web.vo

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime
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
        var createTime: LocalDateTime? = null,

        @ApiModelProperty("订单项列表")
        var newBeeMallOrderItemVOS: List<MallOrderItemVo>? = null
)
