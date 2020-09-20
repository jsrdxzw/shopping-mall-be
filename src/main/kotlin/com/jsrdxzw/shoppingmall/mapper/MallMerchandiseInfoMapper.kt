package com.jsrdxzw.shoppingmall.mapper;

import com.jsrdxzw.shoppingmall.entity.MallMerchandiseInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.jsrdxzw.shoppingmall.service.dto.StockDTO
import com.jsrdxzw.shoppingmall.web.bo.MerchandiseSearchBo
import com.jsrdxzw.shoppingmall.web.vo.MallMerchandiseVo
import org.apache.ibatis.annotations.Param

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jsrdxzw
 * @since 2020-08-29
 */
interface MallMerchandiseInfoMapper : BaseMapper<MallMerchandiseInfo> {
    /**
     * 分页查询商品
     */
    fun searchMerchandise(page: Page<MallMerchandiseVo>, @Param("query") merchandiseSearchBo: MerchandiseSearchBo, @Param("status") status: Int): IPage<MallMerchandiseVo>

    fun updateMerchandiseStock(@Param("stockDTOs") stockDTOs: List<StockDTO>): Int
}
