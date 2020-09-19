package com.jsrdxzw.shoppingmall.web.vo

import java.time.LocalDateTime

/**
 * @author  xuzhiwei
 * @date  2020/09/19
 */
data class MallUserLogin(
        val userId: Long,
        val nickName: String,
        val loginName: String,
        /**
         * 个性签名
         */
        val introduceSign: String,

        /**
         * 注销标识字段(0-正常 1-已注销)
         */
        val isDeleted: Int,

        /**
         * 锁定标识字段(0-未锁定 1-已锁定)
         */
        val lockedFlag: Int,

        /**
         * 注册时间
         */
        val createTime: LocalDateTime
)
