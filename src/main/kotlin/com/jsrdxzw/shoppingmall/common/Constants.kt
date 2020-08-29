package com.jsrdxzw.shoppingmall.common

/**
 * @author  xuzhiwei
 * @date  2020/08/25
 */
object Constants {
    /**
     * 本地图片上传地址
     */
    const val FILE_UPLOAD_DIC = "/Users/xuzhiwei/develop/upload"

    const val INDEX_CAROUSEL_NUMBER = 5

    const val INDEX_CATEGORY_NUMBER = 10 //首页一级分类的最大数量

    const val SEARCH_CATEGORY_NUMBER = 8 //搜索页一级分类的最大数量

    const val INDEX_MERCHANDISE_HOT_NUMBER = 4 //首页热卖商品数量

    const val INDEX_MERCHANDISE_NEW_NUMBER = 5 //首页新品数量

    const val INDEX_MERCHANDISE_RECOMMEND_NUMBER = 10 //首页推荐商品数量

    const val SHOPPING_CART_ITEM_TOTAL_NUMBER = 20 //购物车中商品的最大数量(可根据自身需求修改)

    const val SHOPPING_CART_ITEM_LIMIT_NUMBER = 5 //购物车中单个商品的最大购买数量(可根据自身需求修改)

    const val MALL_USER_SESSION_KEY = "newBeeMallUser" //session中user的key

    const val MERCHANDISE_SEARCH_PAGE_LIMIT = 10 //搜索分页的默认条数(每页10条)

    const val SHOPPING_CART_PAGE_LIMIT = 5 //购物车分页的默认条数(每页5条)

    const val USER_ADDRESS_PAGE_LIMIT = 10 //购物车分页的默认条数(每页10条)

    const val ORDER_SEARCH_PAGE_LIMIT = 5 //我的订单列表分页的默认条数(每页5条)

    const val SELL_STATUS_UP = 0 //商品上架状态

    const val SELL_STATUS_DOWN = 1 //商品下架状态

    const val TOKEN_LENGTH = 32 //token字段长度

    const val USER_INTRO = "随新所欲，蜂富多彩" //默认简介

}
