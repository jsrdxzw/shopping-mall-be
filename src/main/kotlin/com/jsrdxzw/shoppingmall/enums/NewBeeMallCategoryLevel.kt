package com.jsrdxzw.shoppingmall.enums

import com.jsrdxzw.shoppingmall.exception.MallException

/**
 * @author  xuzhiwei
 * @date  2020/08/26
 */
enum class NewBeeMallCategoryLevel(val level: Int, val desc: String) {
    DEFAULT(0, "ERROR"),
    LEVEL_ONE(1, "一级分类"),
    LEVEL_TWO(2, "二级分类"),
    LEVEL_THREE(3, "三级分类");

    companion object {
        fun getIndexConfigTypeByType(level: Int?): NewBeeMallCategoryLevel {
            return level?.let { levelInner ->
                values().find { it.level == levelInner } ?: DEFAULT
            } ?: throw MallException("分类不能为空")
        }
    }
}
