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
 * @since 2020-08-26
 */
class TbNewbeeMallShoppingCartItem : Serializable {


    /**
     * 购物项主键id
     */
    @TableId(value = "cart_item_id", type = IdType.AUTO)
    var cartItemId: Long? = null

    /**
     * 用户主键id
     */
    var userId: Long? = null

    /**
     * 关联商品id
     */
    var goodsId: Long? = null

    /**
     * 数量(最大为5)
     */
    var goodsCount: Int? = null

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
        return "TbNewbeeMallShoppingCartItem{" +
        "cartItemId=" + cartItemId +
        ", userId=" + userId +
        ", goodsId=" + goodsId +
        ", goodsCount=" + goodsCount +
        ", isDeleted=" + isDeleted +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}"
    }
}
