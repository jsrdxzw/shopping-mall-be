package com.jsrdxzw.shoppingmall.mapper;

import com.jsrdxzw.shoppingmall.entity.MallMerchandiseCategory;
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
interface MallMerchandiseCategoryMapper : BaseMapper<MallMerchandiseCategory> {
    fun selectByParentIdsAndLevelAndLimit(@Param("parentIds") parentIds: List<Long>,
                                          @Param("level") level: Int,
                                          @Param("limit") limit: Int): List<MallMerchandiseCategory>
}
