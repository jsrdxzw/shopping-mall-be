package com.jsrdxzw.shoppingmall.web.vo

import io.swagger.annotations.ApiModelProperty

data class MallUserAddressVo(
        @ApiModelProperty("地址id")
        var id: Long? = null,

        @ApiModelProperty("用户id")
        var userId: Long? = null,

        @ApiModelProperty("收件人名称")
        var userName: String? = null,

        @ApiModelProperty("收件人联系方式")
        var userPhone: String? = null,

        @ApiModelProperty("是否默认地址 0-不是 1-是")
        var defaultFlag: Int? = null,

        @ApiModelProperty("省")
        var provinceName: String? = null,

        @ApiModelProperty("市")
        var cityName: String? = null,

        @ApiModelProperty("区/县")
        var regionName: String? = null,

        @ApiModelProperty("详细地址")
        var detailAddress: String? = null
)
