package com.jsrdxzw.shoppingmall.service

import com.jsrdxzw.shoppingmall.extension.copyList
import com.jsrdxzw.shoppingmall.mapper.MallCarouselMapper
import com.jsrdxzw.shoppingmall.web.vo.MallIndexCarouselVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author  xuzhiwei
 * @date  2020/08/28
 */
@Service
class MallCarouselService {
    @Autowired
    private lateinit var carouselMapper: MallCarouselMapper

    fun getCarouselsForIndex(indexCarouselNumber: Int): List<MallIndexCarouselVo> {
        val mallCarouselList = carouselMapper.selectByNumber(indexCarouselNumber)
        var mallCarouselVoList = emptyList<MallIndexCarouselVo>()
        if (!mallCarouselList.isNullOrEmpty()) {
            mallCarouselVoList = mallCarouselList.copyList(MallIndexCarouselVo::class.java)
        }
        return mallCarouselVoList
    }
}
