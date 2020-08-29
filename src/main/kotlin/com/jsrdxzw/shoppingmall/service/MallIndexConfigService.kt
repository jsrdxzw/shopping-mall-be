package com.jsrdxzw.shoppingmall.service

import com.jsrdxzw.shoppingmall.enums.IndexConfigType
import com.jsrdxzw.shoppingmall.extension.BeanUtilsExt
import com.jsrdxzw.shoppingmall.mapper.MallIndexConfigMapper
import com.jsrdxzw.shoppingmall.mapper.MallMerchandiseInfoMapper
import com.jsrdxzw.shoppingmall.web.vo.MallIndexMerchandiseConfigVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author  xuzhiwei
 * @date  2020/08/28
 */
@Service
class MallIndexConfigService {
    @Autowired
    private lateinit var indexConfigMapper: MallIndexConfigMapper

    @Autowired
    private lateinit var merchandiseInfoMapper: MallMerchandiseInfoMapper

    fun getConfigMerchandisesForIndex(indexConfigType: IndexConfigType, indexMerchandiseHotNumber: Int): List<MallIndexMerchandiseConfigVo> {
        val indexConfigList = indexConfigMapper.findIndexConfigsByTypeAndNumber(indexConfigType.type, indexMerchandiseHotNumber)
        var result = emptyList<MallIndexMerchandiseConfigVo>()
        if (!indexConfigList.isNullOrEmpty()) {
            // 获取所有的商品信息
            val merchandiseIdList = indexConfigList.mapNotNull { it.merchandiseId }
            val merchandiseInfoList = merchandiseInfoMapper.selectBatchIds(merchandiseIdList)
            result = BeanUtilsExt.copyList(list = merchandiseInfoList, clazz = MallIndexMerchandiseConfigVo::class.java)
            for (mallIndexMerchandiseConfigVo in result) {
                val merchandiseName = mallIndexMerchandiseConfigVo.merchandiseName
                val merchandiseIntro = mallIndexMerchandiseConfigVo.merchandiseIntro
                if (!merchandiseName.isNullOrBlank() && merchandiseName.length > MERCHANDISE_NAME_MAX_LENGTH) {
                    mallIndexMerchandiseConfigVo.merchandiseName = "${merchandiseName.substring(0, MERCHANDISE_NAME_MAX_LENGTH)}..."
                }
                if (!merchandiseIntro.isNullOrBlank() && merchandiseIntro.length > MERCHANDISE_INTRO_MAX_LENGTH) {
                    mallIndexMerchandiseConfigVo.merchandiseName = "${merchandiseIntro.substring(0, MERCHANDISE_INTRO_MAX_LENGTH)}..."
                }
            }
        }
        return result
    }

    companion object {
        private const val MERCHANDISE_NAME_MAX_LENGTH = 30
        private const val MERCHANDISE_INTRO_MAX_LENGTH = 22
    }
}
