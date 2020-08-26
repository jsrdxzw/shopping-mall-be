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
class TbNewbeeMallGoodsCategory : Serializable {


    /**
     * 分类id
     */
    @TableId(value = "category_id", type = IdType.AUTO)
    var categoryId: Long? = null

    /**
     * 分类级别(1-一级分类 2-二级分类 3-三级分类)
     */
    var categoryLevel: Int? = null

    /**
     * 父分类id
     */
    var parentId: Long? = null

    /**
     * 分类名称
     */
    var categoryName: String? = null

    /**
     * 排序值(字段越大越靠前)
     */
    var categoryRank: Int? = null

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
        return "TbNewbeeMallGoodsCategory{" +
        "categoryId=" + categoryId +
        ", categoryLevel=" + categoryLevel +
        ", parentId=" + parentId +
        ", categoryName=" + categoryName +
        ", categoryRank=" + categoryRank +
        ", isDeleted=" + isDeleted +
        ", createTime=" + createTime +
        ", createUser=" + createUser +
        ", updateTime=" + updateTime +
        ", updateUser=" + updateUser +
        "}"
    }
}
