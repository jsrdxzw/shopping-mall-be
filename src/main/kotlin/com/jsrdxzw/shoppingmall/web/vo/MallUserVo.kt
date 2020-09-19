package com.jsrdxzw.shoppingmall.web.vo

import io.swagger.annotations.ApiModelProperty

data class MallUserVo(
        @ApiModelProperty("用户昵称")
        val nickname: String,
        @ApiModelProperty("用户登录名")
        val loginName: String,
        @ApiModelProperty("个性签名")
        val introduceSign: String
)
