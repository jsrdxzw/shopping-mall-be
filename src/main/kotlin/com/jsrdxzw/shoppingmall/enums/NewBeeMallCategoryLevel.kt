package com.jsrdxzw.shoppingmall.enums

import com.jsrdxzw.shoppingmall.exception.NewBeeMallException

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
            level?.let { levelInner ->
                return values().find { it.level == levelInner } ?: DEFAULT
            } ?: throw NewBeeMallException("level不能为空")
        }
    }
}
