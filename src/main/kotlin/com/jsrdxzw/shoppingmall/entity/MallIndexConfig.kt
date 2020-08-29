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
class MallIndexConfig : Serializable {


    /**
     * 首页配置项主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    var id: Long? = null

    /**
     * 显示字符(配置搜索时不可为空，其他可为空)
     */
    var configName: String? = null

    /**
     * 1-搜索框热搜 2-搜索下拉框热搜 3-(首页)热销商品 4-(首页)新品上线 5-(首页)为你推荐
     */
    var configType: Int? = null

    /**
     * 商品id 默认为0
     */
    var goodsId: Long? = null

    /**
     * 点击后的跳转地址(默认不跳转)
     */
    var redirectUrl: String? = null

    /**
     * 排序值(字段越大越靠前)
     */
    var configRank: Int? = null

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
     * 最新修改时间
     */
    var updateTime: LocalDateTime? = null

    /**
     * 修改者id
     */
    var updateUser: Int? = null


    override fun toString(): String {
        return "MallIndexConfig{" +
        "id=" + id +
        ", configName=" + configName +
        ", configType=" + configType +
        ", goodsId=" + goodsId +
        ", redirectUrl=" + redirectUrl +
        ", configRank=" + configRank +
        ", isDeleted=" + isDeleted +
        ", createTime=" + createTime +
        ", createUser=" + createUser +
        ", updateTime=" + updateTime +
        ", updateUser=" + updateUser +
        "}"
    }
}
