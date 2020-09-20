package com.jsrdxzw.shoppingmall.enums

/**
 * @author  xuzhiwei
 * @date  2020/08/26
 */
enum class ServiceResult(val result: String) {
    ERROR("error"),

    PARAM_ERROR("参数错误"),

    SUCCESS("success"),

    DATA_NOT_EXIST("未查询到记录！"),

    SAME_CATEGORY_EXIST("有同级同名的分类！"),

    SAME_LOGIN_NAME_EXIST("用户名已存在！"),

    LOGIN_NAME_NULL("请输入登录名！"),

    LOGIN_NAME_IS_NOT_PHONE("请输入正确的手机号！"),

    LOGIN_PASSWORD_NULL("请输入密码！"),

    LOGIN_VERIFY_CODE_NULL("请输入验证码！"),

    LOGIN_VERIFY_CODE_ERROR("验证码错误！"),

    GOODS_NOT_EXIST("商品不存在！"),

    GOODS_PUT_DOWN("商品已下架！"),

    SHOPPING_ITEM_EMPTY("购物车不能为空！"),

    SHOPPING_CART_ITEM_LIMIT_NUMBER_ERROR("超出单个商品的最大购买数量！"),

    SHOPPING_CART_ITEM_NUMBER_ERROR("商品数量不能小于 1 ！"),

    SHOPPING_CART_ITEM_TOTAL_NUMBER_ERROR("超出购物车最大容量！"),

    SHOPPING_CART_ITEM_EXIST_ERROR("已存在！无需重复添加！"),

    LOGIN_ERROR("登录失败！"),

    NOT_LOGIN_ERROR("未登录！"),

    TOKEN_EXPIRE_ERROR("无效认证！请重新登录！"),

    USER_NULL_ERROR("无效用户！请重新登录！"),

    LOGIN_USER_LOCKED_ERROR("用户已被禁止登录！"),

    ORDER_NOT_EXIST_ERROR("订单不存在！"),

    NULL_ADDRESS_ERROR("地址不能为空！"),

    ORDER_PRICE_ERROR("订单价格异常！"),

    ORDER_SELLING_STATUS_ERROR("订单包含已下架商品！"),

    ORDER_ITEM_NULL_ERROR("订单项异常！"),

    ORDER_GENERATE_ERROR("生成订单异常！"),

    SHOPPING_ITEM_ERROR("购物车数据异常！"),

    SHOPPING_ITEM_COUNT_ERROR("库存不足！"),

    ORDER_STATUS_ERROR("订单状态异常！"),

    ORDER_STATUS_EMPTY_ERROR("订单状态不能为空！"),

    PAY_TYPE_EMPTY_ERROR("支付类型不能为空！"),

    PAY_SUCCESS_ERROR("非待支付状态下的订单无法支付！"),

    OPERATE_ERROR("操作失败！"),

    REQUEST_FORBIDEN_ERROR("禁止该操作！"),

    DB_ERROR("database error"),

    LOGOUT_ERROR("logout error"),

    UPDATE_USER_ERROR("用户更新失败")
}
