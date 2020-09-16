package com.jsrdxzw.shoppingmall.web.bo

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotBlank

@ApiModel("用户信息")
data class MallUserUpdateBo(
        @field:NotBlank(message = "用户昵称不能为空")
        @ApiModelProperty("用户昵称")
        val nickName: String,

        @field:NotBlank(message = "用户密码不能为空")
        @ApiModelProperty("用户密码(需要MD5加密)")
        val passwordMd5: String,

        @ApiModelProperty("个性签名")
        val introduceSign: String?
)
