package com.jsrdxzw.shoppingmall.enums

import com.jsrdxzw.shoppingmall.exception.NewBeeMallException

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
    INDEX_GOODS_HOT(3, "INDEX_GOODS_HOTS"),

    /**
     * (首页)新品上线
     */
    INDEX_GOODS_NEW(4, "INDEX_GOODS_NEW"),

    /**
     * (首页)为你推荐
     */
    INDEX_GOODS_RECOMMEND(5, "INDEX_GOODS_RECOMMEND");

    companion object {
        fun getIndexConfigTypeByType(type: Int?): IndexConfigType {
            type?.let { typeInner ->
                return values().find { it.type == typeInner } ?: DEFAULT
            } ?: throw NewBeeMallException("type不能为空")
        }
    }
}
