package com.jsrdxzw.shoppingmall.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import java.io.Serializable
/**
 * <p>
 * 
 * </p>
 *
 * @author jsrdxzw
 * @since 2020-08-28
 */
class MallAdminUser : Serializable {


    /**
     * 管理员id
     */
    @TableId(value = "id", type = IdType.AUTO)
    var id: Int? = null

    /**
     * 管理员登陆名称
     */
    var loginUserName: String? = null

    /**
     * 管理员登陆密码
     */
    var loginPassword: String? = null

    /**
     * 管理员显示昵称
     */
    var nickName: String? = null

    /**
     * 是否锁定 0未锁定 1已锁定无法登陆
     */
    var locked: Int? = null


    override fun toString(): String {
        return "MallAdminUser{" +
        "id=" + id +
        ", loginUserName=" + loginUserName +
        ", loginPassword=" + loginPassword +
        ", nickName=" + nickName +
        ", locked=" + locked +
        "}"
    }
}
