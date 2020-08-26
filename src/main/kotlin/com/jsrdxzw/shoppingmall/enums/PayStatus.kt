package com.jsrdxzw.shoppingmall.enums

import com.jsrdxzw.shoppingmall.exception.NewBeeMallException

/**
 * @author  xuzhiwei
 * @date  2020/08/26
 */
enum class PayStatus(val payStatus: Int, val desc: String) {
    DEFAULT(-1, "支付失败"),
    PAY_ING(0, "支付中"),
    PAY_SUCCESS(1, "支付成功");

    companion object {
        fun getPayStatusByStatus(payStatus: Int?): PayStatus {
            return payStatus?.let { payStatusInner ->
                values().find { it.payStatus == payStatusInner } ?: DEFAULT
            } ?: throw NewBeeMallException("支付状态不能为空")
        }
    }
}
