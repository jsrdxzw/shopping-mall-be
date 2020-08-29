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
class MallUserToken : Serializable {


    /**
     * token主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    var id: Long? = null

    /**
     * 用户主键id
     */
    var userId: Long? = null

    /**
     * token值(32位字符串)
     */
    var token: String? = null

    /**
     * 修改时间
     */
    var updateTime: LocalDateTime? = null

    /**
     * token过期时间
     */
    var expireTime: LocalDateTime? = null


    override fun toString(): String {
        return "MallUserToken{" +
        "id=" + id +
        ", userId=" + userId +
        ", token=" + token +
        ", updateTime=" + updateTime +
        ", expireTime=" + expireTime +
        "}"
    }
}
