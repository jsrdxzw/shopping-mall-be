package com.jsrdxzw.shoppingmall.web.controller

import com.jsrdxzw.shoppingmall.exception.MallException
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

/**
 * @author  xuzhiwei
 * @date  2020/08/29
 */
@Api(tags = ["新蜂商城商品相关接口"])
@RestController
@RequestMapping("/merchandise")
@Validated
class MerchandiseController {
    @GetMapping("/search")
    @ApiOperation(value = "商品搜索接口", notes = "根据关键字和分类id进行搜索")
    fun searchMerchandise(
            @RequestParam(required = false) @ApiParam("搜索关键字") keyword: String?,
            @RequestParam(required = false) @ApiParam("分类id") merchandiseCategoryId: Long?,
            @RequestParam(required = false) @ApiParam("orderBy") orderBy: String?,
            @Valid @NotNull @Min(1) @RequestParam(defaultValue = "1") @ApiParam("页码") page: Int,
            @Valid @NotNull @Min(1) @RequestParam(defaultValue = "10") @ApiParam("每页返回数") size: Int
    ) {
        if (merchandiseCategoryId == null || keyword.isNullOrBlank()) {
            MallException.fail("非法的搜索参数")
        }

    }
}
