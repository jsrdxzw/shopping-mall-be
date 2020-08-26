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
class TbNewbeeMallGoodsInfo : Serializable {


    /**
     * 商品表主键id
     */
    @TableId(value = "goods_id", type = IdType.AUTO)
    var goodsId: Long? = null

    /**
     * 商品名
     */
    var goodsName: String? = null

    /**
     * 商品简介
     */
    var goodsIntro: String? = null

    /**
     * 关联分类id
     */
    var goodsCategoryId: Long? = null

    /**
     * 商品主图
     */
    var goodsCoverImg: String? = null

    /**
     * 商品轮播图
     */
    var goodsCarousel: String? = null

    /**
     * 商品详情
     */
    var goodsDetailContent: String? = null

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
    var goodsSellStatus: Int? = null

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
        return "TbNewbeeMallGoodsInfo{" +
        "goodsId=" + goodsId +
        ", goodsName=" + goodsName +
        ", goodsIntro=" + goodsIntro +
        ", goodsCategoryId=" + goodsCategoryId +
        ", goodsCoverImg=" + goodsCoverImg +
        ", goodsCarousel=" + goodsCarousel +
        ", goodsDetailContent=" + goodsDetailContent +
        ", originalPrice=" + originalPrice +
        ", sellingPrice=" + sellingPrice +
        ", stockNum=" + stockNum +
        ", tag=" + tag +
        ", goodsSellStatus=" + goodsSellStatus +
        ", createUser=" + createUser +
        ", createTime=" + createTime +
        ", updateUser=" + updateUser +
        ", updateTime=" + updateTime +
        "}"
    }
}
