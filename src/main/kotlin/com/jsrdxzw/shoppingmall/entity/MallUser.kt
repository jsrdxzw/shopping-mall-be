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
class MallUser : Serializable {


    /**
     * 用户主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    var id: Long? = null

    /**
     * 用户昵称
     */
    var nickName: String? = null

    /**
     * 登陆名称(默认为手机号)
     */
    var loginName: String? = null

    /**
     * MD5加密后的密码
     */
    var passwordMd5: String? = null

    /**
     * 个性签名
     */
    var introduceSign: String? = null

    /**
     * 注销标识字段(0-正常 1-已注销)
     */
    var isDeleted: Int? = null

    /**
     * 锁定标识字段(0-未锁定 1-已锁定)
     */
    var lockedFlag: Int? = null

    /**
     * 注册时间
     */
    var createTime: LocalDateTime? = null


    override fun toString(): String {
        return "MallUser{" +
        "id=" + id +
        ", nickName=" + nickName +
        ", loginName=" + loginName +
        ", passwordMd5=" + passwordMd5 +
        ", introduceSign=" + introduceSign +
        ", isDeleted=" + isDeleted +
        ", lockedFlag=" + lockedFlag +
        ", createTime=" + createTime +
        "}"
    }
}
