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
class MallMerchandiseInfo : Serializable {


    /**
     * 商品表主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    var id: Long? = null

    /**
     * 商品名
     */
    var merchandiseName: String? = null

    /**
     * 商品简介
     */
    var merchandiseIntro: String? = null

    /**
     * 关联分类id
     */
    var merchandiseCategoryId: Long? = null

    /**
     * 商品主图
     */
    var merchandiseCoverImg: String? = null

    /**
     * 商品轮播图
     */
    var merchandiseCarousel: String? = null

    /**
     * 商品详情
     */
    var merchandiseDetailContent: String? = null

    /**
     * 商品价格
     */
    var originalPrice: Int? = null

    /**
     * 商品实际售价
     */
    var sellingPrice: Int? = null

    /**
     * 商品库存数量
     */
    var stockNum: Int? = null

    /**
     * 商品标签
     */
    var tag: String? = null

    /**
     * 商品上架状态 1-下架 0-上架
     */
    var merchandiseSellStatus: Int? = null

    /**
     * 添加者主键id
     */
    var createUser: Int? = null

    /**
     * 商品添加时间
     */
    var createTime: LocalDateTime? = null

    /**
     * 修改者主键id
     */
    var updateUser: Int? = null

    /**
     * 商品修改时间
     */
    var updateTime: LocalDateTime? = null


    override fun toString(): String {
        return "MallMerchandiseInfo{" +
        "id=" + id +
        ", merchandiseName=" + merchandiseName +
        ", merchandiseIntro=" + merchandiseIntro +
        ", merchandiseCategoryId=" + merchandiseCategoryId +
        ", merchandiseCoverImg=" + merchandiseCoverImg +
        ", merchandiseCarousel=" + merchandiseCarousel +
        ", merchandiseDetailContent=" + merchandiseDetailContent +
        ", originalPrice=" + originalPrice +
        ", sellingPrice=" + sellingPrice +
        ", stockNum=" + stockNum +
        ", tag=" + tag +
        ", merchandiseSellStatus=" + merchandiseSellStatus +
        ", createUser=" + createUser +
        ", createTime=" + createTime +
        ", updateUser=" + updateUser +
        ", updateTime=" + updateTime +
        "}"
    }
}
