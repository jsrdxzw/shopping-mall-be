package com.jsrdxzw.shoppingmall.extension

import org.springframework.beans.BeanUtils

/**
 * @author  xuzhiwei
 * @date  2020/08/29
 */
object BeanUtilsExt {
    fun <T> copyList(list: List<*>?, clazz: Class<T>?): List<T> {
        val mutableList = mutableListOf<T>()
        if (list == null || clazz == null) {
            return mutableList
        }
        for (element in list) {
            val target = clazz.getDeclaredConstructor().newInstance()
            element?.let {
                BeanUtils.copyProperties(it, target!!)
                mutableList.add(target)
            }
        }
        return mutableList
    }
}
