package com.jsrdxzw.shoppingmall.entity

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
class TbNewbeeMallUserToken : Serializable {


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
        return "TbNewbeeMallUserToken{" +
        "userId=" + userId +
        ", token=" + token +
        ", updateTime=" + updateTime +
        ", expireTime=" + expireTime +
        "}"
    }
}
