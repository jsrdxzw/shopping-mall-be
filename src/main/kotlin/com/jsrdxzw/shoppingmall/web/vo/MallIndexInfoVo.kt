package com.jsrdxzw.shoppingmall.web.vo

import io.swagger.annotations.ApiModelProperty

data class MallIndexInfoVo(
        @ApiModelProperty("轮播图(列表)")
        var carousels: List<MallIndexCarouselVo>? = null,
        @ApiModelProperty("首页热销商品(列表)")
        var hotMerchandise: List<MallIndexMerchandiseVo>? = null,
        @ApiModelProperty("首页新品推荐(列表)")
        var newMerchandises: List<MallIndexMerchandiseVo>? = null,
        @ApiModelProperty("首页推荐商品(列表)")
        var recommendMerchandises: List<MallIndexMerchandiseVo>? = null
)
