package com.jsrdxzw.shoppingmall.extension

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import com.jsrdxzw.shoppingmall.enums.ServiceResult
import com.jsrdxzw.shoppingmall.exception.MallException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.BeanUtils
import org.springframework.util.DigestUtils

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

/**
 * 生成订单流水号
 *
 * @return
 */
fun generateOrderNumber(): String {
    val buffer = StringBuffer(System.currentTimeMillis().toString())
    val num: Int = randomNumber(4)
    buffer.append(num)
    return buffer.toString()
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

fun <T : Any, K : Any> T.beanCopy(clazz: Class<K>): K {
    val target = clazz.getDeclaredConstructor().newInstance()
    BeanUtils.copyProperties(this, target)
    return target
}

fun String.md5() = DigestUtils.md5DigestAsHex(this.toByteArray())

inline fun <reified T : Any> lambdaQueryWrapper() = KtQueryWrapper(T::class.java)

inline fun <reified T : Any> lambdaUpdateWrapper() = KtUpdateWrapper(T::class.java)

inline fun <reified T : Any> getLogger(): Logger = LoggerFactory.getLogger(T::class.java)

inline fun validateUpdateSuccess(block: () -> Any) {
    val res = block()
    if (res is Int && res < 1) {
        MallException.fail(ServiceResult.DB_ERROR)
    } else if (res is Boolean && !res) {
        MallException.fail(ServiceResult.DB_ERROR)
    }
}

inline fun updateSuccessCallback(block: () -> Int, callback: () -> Unit) {
    if (block() > 0) {
        callback()
    }
}
