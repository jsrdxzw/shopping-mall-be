package com.jsrdxzw.shoppingmall.web.controller

import com.jsrdxzw.shoppingmall.common.Constants
import com.jsrdxzw.shoppingmall.service.MallCarouselService
import com.jsrdxzw.shoppingmall.service.MallIndexConfigService
import com.jsrdxzw.shoppingmall.util.ResultData
import com.jsrdxzw.shoppingmall.web.vo.MallIndexCarouselVo
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author  xuzhiwei
 * @date  2020/08/28
 */
@Api(tags = ["新蜂商城首页接口"])
@RestController
class MallIndexController {
    @Autowired
    private lateinit var mallCarouselService: MallCarouselService

    @Autowired
    private lateinit var mallIndexConfigService: MallIndexConfigService

    @GetMapping("/index-infos")
    @ApiOperation(value = "获取首页数据", notes = "轮播图、新品、推荐等")
    fun indexInfo(): ResultData<List<MallIndexCarouselVo>> {
        val carouselList = mallCarouselService.getCarouselsForIndex(Constants.INDEX_CAROUSEL_NUMBER)
        return ResultData.success(carouselList)
    }
}
