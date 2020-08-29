package com.jsrdxzw.shoppingmall.annotation

/**
 * @author  xuzhiwei
 * @date  2020/08/27
 */
@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class TokenToMallUser(
        /**
         * 当前用户在request中的名字
         */
        val value: String = "user"
)
