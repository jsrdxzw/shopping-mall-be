package com.jsrdxzw.shoppingmall.mapper;

import com.jsrdxzw.shoppingmall.entity.MallIndexConfig;
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
interface MallIndexConfigMapper : BaseMapper<MallIndexConfig> {
    fun findIndexConfigsByTypeAndNumber(@Param("type") type: Int, @Param("number") indexMerchandiseHotNumber: Int): List<MallIndexConfig>
}
