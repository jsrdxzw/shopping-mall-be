package com.jsrdxzw.shoppingmall.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import java.time.LocalDateTime
import java.io.Serializable
/**
 * <p>
 * 收货地址表
 * </p>
 *
 * @author jsrdxzw
 * @since 2020-08-26
 */
class TbNewbeeMallUserAddress : Serializable {


    @TableId(value = "address_id", type = IdType.AUTO)
    var addressId: Long? = null

    /**
     * 用户主键id
     */
    var userId: Long? = null

    /**
     * 收货人姓名
     */
    var userName: String? = null

    /**
     * 收货人手机号
     */
    var userPhone: String? = null

    /**
     * 是否为默认 0-非默认 1-是默认
     */
    var defaultFlag: Int? = null

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

    /**
     * 删除标识字段(0-未删除 1-已删除)
     */
    var isDeleted: Int? = null

    /**
     * 添加时间
     */
    var createTime: LocalDateTime? = null

    /**
     * 修改时间
     */
    var updateTime: LocalDateTime? = null


    override fun toString(): String {
        return "TbNewbeeMallUserAddress{" +
        "addressId=" + addressId +
        ", userId=" + userId +
        ", userName=" + userName +
        ", userPhone=" + userPhone +
        ", defaultFlag=" + defaultFlag +
        ", provinceName=" + provinceName +
        ", cityName=" + cityName +
        ", regionName=" + regionName +
        ", detailAddress=" + detailAddress +
        ", isDeleted=" + isDeleted +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}"
    }
}
