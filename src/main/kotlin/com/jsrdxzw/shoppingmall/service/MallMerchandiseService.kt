package com.jsrdxzw.shoppingmall.service

import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.jsrdxzw.shoppingmall.common.Constants
import com.jsrdxzw.shoppingmall.entity.MallMerchandiseInfo
import com.jsrdxzw.shoppingmall.enums.ActiveType
import com.jsrdxzw.shoppingmall.extension.lambdaQueryWrapper
import com.jsrdxzw.shoppingmall.mapper.MallMerchandiseInfoMapper
import com.jsrdxzw.shoppingmall.web.bo.MerchandiseSearchBo
import com.jsrdxzw.shoppingmall.web.vo.MallMerchandiseVo
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author  xuzhiwei
 * @date  2020/08/30
 */
@Service
class MallMerchandiseService {
    @Autowired
    private lateinit var merchandiseInfoMapper: MallMerchandiseInfoMapper

    fun searchMerchandises(merchandiseSearchBo: MerchandiseSearchBo): IPage<MallMerchandiseVo> {
        val mallMerchandiseInfoPage: IPage<MallMerchandiseVo> = merchandiseInfoMapper.searchMerchandise(
                Page(merchandiseSearchBo.page, merchandiseSearchBo.size), merchandiseSearchBo, ActiveType.ACTIVE.type)
        mallMerchandiseInfoPage.records.forEach {
            val merchandiseName = it.merchandiseName
            val merchandiseIntro = it.merchandiseIntro
            if (!merchandiseName.isNullOrBlank() && merchandiseName.length > Constants.MERCHANDISE_NAME_LENGTH) {
                it.merchandiseName = merchandiseName.substring(0, Constants.MERCHANDISE_NAME_LENGTH)
            }
            if (!merchandiseIntro.isNullOrBlank() && merchandiseIntro.length > Constants.MERCHANDISE_INTRO_LENGTH) {
                it.merchandiseIntro = merchandiseIntro.substring(0, Constants.MERCHANDISE_INTRO_LENGTH)
            }
        }
        return mallMerchandiseInfoPage
    }

    fun searchMerchandiseById(merchandiseId: Int): MallMerchandiseVo {
        val queryWrapper = lambdaQueryWrapper<MallMerchandiseInfo>()
                .eq(MallMerchandiseInfo::id, merchandiseId)
                .eq(MallMerchandiseInfo::merchandiseSellStatus, ActiveType.ACTIVE.type)
        val merchandiseInfo: MallMerchandiseInfo? = merchandiseInfoMapper.selectOne(queryWrapper)
        val merchandiseVo = MallMerchandiseVo()
        merchandiseInfo?.let { BeanUtils.copyProperties(it, merchandiseVo) }
        return merchandiseVo
    }
}
