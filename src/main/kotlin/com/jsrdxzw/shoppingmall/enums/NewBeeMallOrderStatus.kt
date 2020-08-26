package com.jsrdxzw.shoppingmall.enums

import com.jsrdxzw.shoppingmall.exception.NewBeeMallException

/**
 * @author  xuzhiwei
 * @date  2020/08/26
 */
enum class NewBeeMallOrderStatus(val orderStatus: Int, val desc: String) {
    DEFAULT(-9, "ERROR"),
    ORDER_PRE_PAY(0, "待支付"),
    ORDER_PAID(1, "已支付"),
    ORDER_PACKAGED(2, "配货完成"),
    ORDER_EXPRESS(3, "出库成功"),
    ORDER_SUCCESS(4, "交易成功"),
    ORDER_CLOSED_BY_MANUALLY(-1, "手动关闭"),
    ORDER_CLOSED_BY_EXPIRED(-2, "超时关闭"),
    ORDER_CLOSED_BY_JUDGE(-3, "商家关闭");

    companion object {
        fun getNewBeeMallOrderStatusByStatus(orderStatus: Int?): NewBeeMallOrderStatus {
            return orderStatus?.let { status ->
                values().find { it.orderStatus == status } ?: DEFAULT
            } ?: throw NewBeeMallException("订单状态不能为空")
        }
    }
}
