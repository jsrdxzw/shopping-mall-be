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
 * @since 2020-08-28
 */
class MallCarousel : Serializable {


    /**
     * 首页轮播图主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    var id: Int? = null

    /**
     * 轮播图
     */
    var carouselUrl: String? = null

    /**
     * 点击后的跳转地址(默认不跳转)
     */
    var redirectUrl: String? = null

    /**
     * 排序值(字段越大越靠前)
     */
    var carouselRank: Int? = null

    /**
     * 删除标识字段(0-未删除 1-已删除)
     */
    var isDeleted: Int? = null

    /**
     * 创建时间
     */
    var createTime: LocalDateTime? = null

    /**
     * 创建者id
     */
    var createUser: Int? = null

    /**
     * 修改时间
     */
    var updateTime: LocalDateTime? = null

    /**
     * 修改者id
     */
    var updateUser: Int? = null


    override fun toString(): String {
        return "MallCarousel{" +
        "id=" + id +
        ", carouselUrl=" + carouselUrl +
        ", redirectUrl=" + redirectUrl +
        ", carouselRank=" + carouselRank +
        ", isDeleted=" + isDeleted +
        ", createTime=" + createTime +
        ", createUser=" + createUser +
        ", updateTime=" + updateTime +
        ", updateUser=" + updateUser +
        "}"
    }
}
