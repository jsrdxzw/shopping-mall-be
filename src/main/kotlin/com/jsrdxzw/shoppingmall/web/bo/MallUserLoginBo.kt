package com.jsrdxzw.shoppingmall.web.bo

import com.jsrdxzw.shoppingmall.annotation.ValidatePhone
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotBlank

/**
 * @author  xuzhiwei
 * @date  2020/09/01
 */
@ApiModel("用户登录请求参数")
data class MallUserLoginBo(
        @ApiModelProperty("登录名")
        @field:ValidatePhone(message = "用户名必须是手机号")
        val loginName: String,
        @ApiModelProperty("用户密码(需要MD5加密)")
        @field:NotBlank(message = "密码不能为空")
        val passwordMd5: String
)
