package com.jsrdxzw.shoppingmall.extension

import java.math.BigInteger
import java.security.MessageDigest

/**
 * @author  xuzhiwei
 * @date  2020/09/14
 */
fun getNewToken(timeStr: String, userId: Long?): String? {
    return genToken("$timeStr$userId${randomNumber(4)}")
}

private fun genToken(src: String?): String? {
    return if (src.isNullOrBlank()) {
        null
    } else try {
        val md = MessageDigest.getInstance("MD5")
        md.update(src.toByteArray())
        var result = BigInteger(1, md.digest()).toString(16)
        if (result.length == 31) {
            result = "$result-"
        }
        result
    } catch (e: Exception) {
        null
    }
}
