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
class MallOrderItem : Serializable {


    /**
     * 订单关联购物项主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    var id: Long? = null

    /**
     * 订单主键id
     */
    var orderId: Long? = null

    /**
     * 关联商品id
     */
    var merchandiseId: Long? = null

    /**
     * 下单时商品的名称(订单快照)
     */
    var goodsName: String? = null

    /**
     * 下单时商品的主图(订单快照)
     */
    var goodsCoverImg: String? = null

    /**
     * 下单时商品的价格(订单快照)
     */
    var sellingPrice: Int? = null

    /**
     * 数量(订单快照)
     */
    var goodsCount: Int? = null

    /**
     * 创建时间
     */
    var createTime: LocalDateTime? = null


    override fun toString(): String {
        return "MallOrderItem{" +
        "id=" + id +
        ", orderId=" + orderId +
        ", merchandiseId=" + merchandiseId +
        ", goodsName=" + goodsName +
        ", goodsCoverImg=" + goodsCoverImg +
        ", sellingPrice=" + sellingPrice +
        ", goodsCount=" + goodsCount +
        ", createTime=" + createTime +
        "}"
    }
}
