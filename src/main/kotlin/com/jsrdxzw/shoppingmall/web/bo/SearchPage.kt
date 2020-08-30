package com.jsrdxzw.shoppingmall.web.bo

import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.Min

/**
 * @author  xuzhiwei
 * @date  2020/08/30
 */
open class SearchPage(
        @ApiModelProperty("搜索页码", notes = "默认从第一页开始")
        @field:Min(1, message = "page不能小于1")
        var page: Long = 1,
        @ApiModelProperty("搜索条数", notes = "默认为10")
        @field:Min(1, message = "pageSize不能小于1")
        var size: Long = 10
)
