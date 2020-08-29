package com.jsrdxzw.shoppingmall.web.vo

import io.swagger.annotations.ApiModelProperty

data class MallIndexCarouselVo(
        @ApiModelProperty("轮播图图片地址")
        var carouselUrl: String? = null,
        @ApiModelProperty("轮播图点击后的跳转路径")
        var redirectUrl: String? = null
)
