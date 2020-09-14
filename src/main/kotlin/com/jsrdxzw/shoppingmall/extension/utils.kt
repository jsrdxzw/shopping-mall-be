package com.jsrdxzw.shoppingmall.extension

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.BeanUtils
import org.springframework.util.DigestUtils
import java.security.MessageDigest

/**
 * @author  xuzhiwei
 * @date  2020/09/01
 */
fun randomNumber(length: Int): Int {
    var num = 1
    var random = Math.random()
    if (random < 0.1) {
        random += 0.1
    }
    for (i in 0 until length) {
        num *= 10
    }
    return (random * num).toInt()
}

fun <T, K : Any> Collection<T>.copyList(clazz: Class<K>): List<K> {
    val result = mutableListOf<K>()
    for (element in this) {
        val item = clazz.getDeclaredConstructor().newInstance()
        if (element != null) {
            BeanUtils.copyProperties(element, item)
            result.add(item)
        }
    }
    return result
}

fun String.md5() = DigestUtils.md5DigestAsHex(this.toByteArray())

inline fun <reified T : Any> lambdaQueryWrapper() = KtQueryWrapper(T::class.java)

inline fun <reified T : Any> getLogger(): Logger = LoggerFactory.getLogger(T::class.java)
