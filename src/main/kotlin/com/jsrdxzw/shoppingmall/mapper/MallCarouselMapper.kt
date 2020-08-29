package com.jsrdxzw.shoppingmall.mapper;

import com.jsrdxzw.shoppingmall.entity.MallCarousel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jsrdxzw
 * @since 2020-08-29
 */
interface MallCarouselMapper : BaseMapper<MallCarousel> {
    fun selectByNumber(@Param("number") indexCarouselNumber: Int): List<MallCarousel>
}
