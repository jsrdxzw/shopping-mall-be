package com.jsrdxzw.shoppingmall.enums

import com.jsrdxzw.shoppingmall.exception.MallException

/**
 * @author  xuzhiwei
 * @date  2020/08/26
 */
enum class IndexConfigType(val type: Int, val desc: String) {
    /**
     * 首页配置项
     */
    DEFAULT(0, "DEFAULT"),

    /**
     * 搜索框热搜
     */
    INDEX_SEARCH_HOTS(1, "INDEX_SEARCH_HOTS"),

    /**
     * 搜索下拉框热搜
     */
    INDEX_SEARCH_DOWN_HOTS(2, "INDEX_SEARCH_DOWN_HOTS"),

    /**
     * (首页)热销商品
     */
    INDEX_MERCHANDISE_HOT(3, "INDEX_MERCHANDISE_HOTS"),

    /**
     * (首页)新品上线
     */
    INDEX_MERCHANDISE_NEW(4, "INDEX_MERCHANDISE_NEW"),

    /**
     * (首页)为你推荐
     */
    INDEX_MERCHANDISE_RECOMMEND(5, "INDEX_MERCHANDISE_RECOMMEND");

    companion object {
        fun getIndexConfigTypeByType(type: Int?): IndexConfigType {
            return type?.let { typeInner ->
                values().find { it.type == typeInner } ?: DEFAULT
            } ?: throw MallException("首页类型不能为空")
        }
    }
}
