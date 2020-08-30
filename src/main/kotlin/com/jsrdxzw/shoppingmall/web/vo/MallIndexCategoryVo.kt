package com.jsrdxzw.shoppingmall.web.vo

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author  xuzhiwei
 * @date  2020/08/30
 */
@ApiModel("首页分类返回实体")
data class MallIndexCategoryVo(
        @ApiModelProperty("id")
        var id: Long? = null,
        @ApiModelProperty("当前分类id")
        var categoryId: Long? = null,
        @ApiModelProperty("父级分类id")
        var parentId: Long? = null,
        @ApiModelProperty("当前分类级别")
        var categoryLevel: Byte? = null,
        @ApiModelProperty("当前分类名称")
        var categoryName: String? = null,
        @ApiModelProperty("子分类列表")
        var childCategory: List<MallIndexCategoryVo>? = null
)
