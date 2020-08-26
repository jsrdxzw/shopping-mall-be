package com.jsrdxzw.shoppingmall.entity

import java.io.Serializable
/**
 * <p>
 * 订单收货地址关联表
 * </p>
 *
 * @author jsrdxzw
 * @since 2020-08-26
 */
class TbNewbeeMallOrderAddress : Serializable {


    var orderId: Long? = null

    /**
     * 收货人姓名
     */
    var userName: String? = null

    /**
     * 收货人手机号
     */
    var userPhone: String? = null

    /**
     * 省
     */
    var provinceName: String? = null

    /**
     * 城
     */
    var cityName: String? = null

    /**
     * 区
     */
    var regionName: String? = null

    /**
     * 收件详细地址(街道/楼宇/单元)
     */
    var detailAddress: String? = null


    override fun toString(): String {
        return "TbNewbeeMallOrderAddress{" +
        "orderId=" + orderId +
        ", userName=" + userName +
        ", userPhone=" + userPhone +
        ", provinceName=" + provinceName +
        ", cityName=" + cityName +
        ", regionName=" + regionName +
        ", detailAddress=" + detailAddress +
        "}"
    }
}
