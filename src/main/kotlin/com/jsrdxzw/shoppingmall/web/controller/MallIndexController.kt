package com.jsrdxzw.shoppingmall.web.controller

import com.jsrdxzw.shoppingmall.common.Constants
import com.jsrdxzw.shoppingmall.enums.IndexConfigType
import com.jsrdxzw.shoppingmall.service.MallCarouselService
import com.jsrdxzw.shoppingmall.service.MallIndexConfigService
import com.jsrdxzw.shoppingmall.util.ResultData
import com.jsrdxzw.shoppingmall.web.vo.MallIndexInfoVo
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author  xuzhiwei
 * @date  2020/08/28
 */
@Api(tags = ["新蜂商城首页接口"])
@RestController
@RequestMapping("/index")
class MallIndexController {
    @Autowired
    private lateinit var mallCarouselService: MallCarouselService

    @Autowired
    private lateinit var mallIndexConfigService: MallIndexConfigService

    @GetMapping("/index-infos")
    @ApiOperation(value = "获取首页数据", notes = "轮播图、新品、推荐等")
    fun indexInfo(): ResultData<MallIndexInfoVo> {
        val carouselList = mallCarouselService.getCarouselsForIndex(Constants.INDEX_CAROUSEL_NUMBER)
        val hotMerchandiseList = mallIndexConfigService.getConfigMerchandisesForIndex(IndexConfigType.INDEX_MERCHANDISE_HOT, Constants.INDEX_MERCHANDISE_HOT_NUMBER)
        val newMerchandiseList = mallIndexConfigService.getConfigMerchandisesForIndex(IndexConfigType.INDEX_MERCHANDISE_NEW, Constants.INDEX_MERCHANDISE_HOT_NUMBER)
        val recommendMerchandiseList = mallIndexConfigService.getConfigMerchandisesForIndex(IndexConfigType.INDEX_MERCHANDISE_RECOMMEND, Constants.INDEX_MERCHANDISE_HOT_NUMBER)
        return ResultData.success(MallIndexInfoVo().also {
            it.carousels = carouselList
            it.hotMerchandise = hotMerchandiseList
            it.newMerchandises = newMerchandiseList
            it.recommendMerchandises = recommendMerchandiseList
        })
    }
}
