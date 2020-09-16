package com.jsrdxzw.shoppingmall.web.bo

import com.jsrdxzw.shoppingmall.annotation.ValidatePhone
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotBlank

@ApiModel("用户注册")
data class MallUserRegisterBo(
        @field:NotBlank(message = "登录名不能为空")
        @field:ValidatePhone(message = "登录名必须为手机号")
        @ApiModelProperty("登录名")
        val loginName: String,

        @field:NotBlank(message = "用户密码不能为空")
        @ApiModelProperty("用户密码")
        val password: String
)
