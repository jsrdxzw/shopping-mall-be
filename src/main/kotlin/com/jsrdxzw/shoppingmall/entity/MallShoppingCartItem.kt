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
class MallShoppingCartItem : Serializable {


    /**
     * 购物项主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    var id: Long? = null

    /**
     * 用户主键id
     */
    var userId: Long? = null

    /**
     * 关联商品id
     */
    var merchandiseId: Long? = null

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
        return "MallShoppingCartItem{" +
        "id=" + id +
        ", userId=" + userId +
        ", merchandiseId=" + merchandiseId +
        ", goodsCount=" + goodsCount +
        ", isDeleted=" + isDeleted +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}"
    }
}
