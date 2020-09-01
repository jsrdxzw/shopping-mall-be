package com.jsrdxzw.shoppingmall.web.bo

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author  xuzhiwei
 * @date  2020/08/30
 */
@ApiModel("商品搜索参数")
data class MerchandiseSearchBo(
        @ApiModelProperty("关键词")
        val keyword: String?,
        @ApiModelProperty("分类id")
        val merchandiseCategoryId: Long?,
        @ApiModelProperty("排序")
        val orderBy: String?
) : SearchPage()
