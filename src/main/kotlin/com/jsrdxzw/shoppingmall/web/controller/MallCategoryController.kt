package com.jsrdxzw.shoppingmall.web.controller

import com.jsrdxzw.shoppingmall.service.MallCategoryService
import com.jsrdxzw.shoppingmall.util.ResultData
import com.jsrdxzw.shoppingmall.web.vo.MallIndexCategoryVo
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author  xuzhiwei
 * @date  2020/08/30
 */
@Api(tags = ["商城分类页面接口"])
@RestController
@RequestMapping("/category")
class MallCategoryController {
    @Autowired
    private lateinit var mallCategoryService: MallCategoryService

    @GetMapping
    @ApiOperation("获取分类数据", notes = "分类页面使用")
    fun getCategories(): ResultData<List<MallIndexCategoryVo>> {
        return ResultData.success(mallCategoryService.getIndexCategories())
    }
}
