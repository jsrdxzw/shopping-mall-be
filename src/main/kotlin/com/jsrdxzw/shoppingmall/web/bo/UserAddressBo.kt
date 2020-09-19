package com.jsrdxzw.shoppingmall.web.bo

import io.swagger.annotations.ApiModelProperty

data class UserAddressBo(
        @ApiModelProperty("收件人名称")
        val userName: String,

        @ApiModelProperty("收件人联系方式")
        val userPhone: String,

        @ApiModelProperty("是否默认地址 0-不是 1-是")
        val defaultFlag: Byte,

        @ApiModelProperty("省")
        val provinceName: String,

        @ApiModelProperty("市")
        val cityName: String,

        @ApiModelProperty("区/县")
        val regionName: String,

        @ApiModelProperty("详细地址")
        val detailAddress: String
)
