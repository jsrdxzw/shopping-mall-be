package com.jsrdxzw.shoppingmall.enums

import com.jsrdxzw.shoppingmall.exception.NewBeeMallException

/**
 * @author  xuzhiwei
 * @date  2020/08/26
 */
enum class PayType(val payType: Int, val desc: String) {
    DEFAULT(-1, "ERROR"),
    NOT_PAY(0, "无"),
    ALI_PAY(1, "支付宝"),
    WEIXIN_PAY(2, "微信支付");

    companion object {
        fun getPayTypeByType(payType: Int?): PayType {
            return payType?.let { payTypeInner ->
                values().find { it.payType == payTypeInner } ?: DEFAULT
            } ?: throw NewBeeMallException("支付类型不能为空")
        }
    }
}