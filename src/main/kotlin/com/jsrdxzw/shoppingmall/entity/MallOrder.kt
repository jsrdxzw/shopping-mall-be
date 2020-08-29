package com.jsrdxzw.shoppingmall.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import java.time.LocalDateTime
import java.io.Serializable
/**
 * <p>
 * 
 * </p>
 *
 * @author jsrdxzw
 * @since 2020-08-29
 */
class MallOrder : Serializable {


    /**
     * 订单表主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    var id: Long? = null

    /**
     * 订单号
     */
    var orderNo: String? = null

    /**
     * 用户主键id
     */
    var userId: Long? = null

    /**
     * 订单总价
     */
    var totalPrice: Int? = null

    /**
     * 支付状态:0.未支付,1.支付成功,-1:支付失败
     */
    var payStatus: Int? = null

    /**
     * 0.无 1.支付宝支付 2.微信支付
     */
    var payType: Int? = null

    /**
     * 支付时间
     */
    var payTime: LocalDateTime? = null

    /**
     * 订单状态:0.待支付 1.已支付 2.配货完成 3:出库成功 4.交易成功 -1.手动关闭 -2.超时关闭 -3.商家关闭
     */
    var orderStatus: Int? = null

    /**
     * 订单body
     */
    var extraInfo: String? = null

    /**
     * 删除标识字段(0-未删除 1-已删除)
     */
    var isDeleted: Int? = null

    /**
     * 创建时间
     */
    var createTime: LocalDateTime? = null

    /**
     * 最新修改时间
     */
    var updateTime: LocalDateTime? = null


    override fun toString(): String {
        return "MallOrder{" +
        "id=" + id +
        ", orderNo=" + orderNo +
        ", userId=" + userId +
        ", totalPrice=" + totalPrice +
        ", payStatus=" + payStatus +
        ", payType=" + payType +
        ", payTime=" + payTime +
        ", orderStatus=" + orderStatus +
        ", extraInfo=" + extraInfo +
        ", isDeleted=" + isDeleted +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}"
    }
}
