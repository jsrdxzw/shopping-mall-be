package com.jsrdxzw.shoppingmall.web.controller

import com.baomidou.mybatisplus.core.metadata.IPage
import com.jsrdxzw.shoppingmall.annotation.TokenToMallUser
import com.jsrdxzw.shoppingmall.entity.MallUser
import com.jsrdxzw.shoppingmall.exception.MallException
import com.jsrdxzw.shoppingmall.service.MallMerchandiseService
import com.jsrdxzw.shoppingmall.util.ResultData
import com.jsrdxzw.shoppingmall.web.bo.MerchandiseSearchBo
import com.jsrdxzw.shoppingmall.web.vo.MallMerchandiseVo
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * @author  xuzhiwei
 * @date  2020/08/29
 */
@Api(tags = ["新蜂商城商品相关接口"])
@RestController
@RequestMapping("/merchandise")
@Validated
class MallMerchandiseController {

    @Autowired
    private lateinit var mallMerchandiseService: MallMerchandiseService

    @GetMapping("/search")
    @ApiOperation(value = "商品搜索接口", notes = "根据关键字和分类id进行搜索")
    fun searchMerchandise(@Valid merchandiseSearchBo: MerchandiseSearchBo): ResultData<IPage<MallMerchandiseVo>> {
        if (merchandiseSearchBo.merchandiseCategoryId == null && merchandiseSearchBo.keyword.isNullOrBlank()) {
            MallException.fail("非法的搜索参数")
        }
        val mallMerchandiseVos: IPage<MallMerchandiseVo> = mallMerchandiseService.searchMerchandises(merchandiseSearchBo)
        return ResultData.success(mallMerchandiseVos)
    }

    @GetMapping("/detail/{merchandiseId}")
    @ApiOperation(value = "商品详情接口", notes = "传参为商品id")
    fun searchMerchandiseDetail(@ApiParam("商品id") @PathVariable("merchandiseId") merchandiseId: Int): ResultData<MallMerchandiseVo> {
        if (merchandiseId < 1) {
            MallException.fail("参数异常")
        }
        val result: MallMerchandiseVo = mallMerchandiseService.searchMerchandiseById(merchandiseId)
        return ResultData.success(result)
    }
}
