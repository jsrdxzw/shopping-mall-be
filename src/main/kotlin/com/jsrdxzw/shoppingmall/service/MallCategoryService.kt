package com.jsrdxzw.shoppingmall.service

import com.jsrdxzw.shoppingmall.common.Constants
import com.jsrdxzw.shoppingmall.entity.MallMerchandiseCategory
import com.jsrdxzw.shoppingmall.enums.NewBeeMallCategoryLevel
import com.jsrdxzw.shoppingmall.extension.copyList
import com.jsrdxzw.shoppingmall.mapper.MallMerchandiseCategoryMapper
import com.jsrdxzw.shoppingmall.web.vo.MallIndexCategoryVo
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author  xuzhiwei
 * @date  2020/08/30
 */
@Service
class MallCategoryService {
    @Autowired
    private lateinit var mallMerchandiseCategoryMapper: MallMerchandiseCategoryMapper
    fun getIndexCategories(): List<MallIndexCategoryVo> {
        val merchandiseCategoryVoList = mutableListOf<MallIndexCategoryVo>()
        val merchandiseCategoryList: List<MallMerchandiseCategory> = mallMerchandiseCategoryMapper.selectByParentIdsAndLevelAndLimit(listOf(0L), NewBeeMallCategoryLevel.LEVEL_ONE.level, Constants.INDEX_CATEGORY_NUMBER)
        if (merchandiseCategoryList.isNotEmpty()) {
            val firstCategoryIds = merchandiseCategoryList.mapNotNull { it.id }
            val secondMerchandiseCategory = mallMerchandiseCategoryMapper.selectByParentIdsAndLevelAndLimit(firstCategoryIds, NewBeeMallCategoryLevel.LEVEL_TWO.level, 0)
            if (secondMerchandiseCategory.isNotEmpty()) {
                val secondCategoryIds = secondMerchandiseCategory.mapNotNull { it.id }
                val thirdCategory = mallMerchandiseCategoryMapper.selectByParentIdsAndLevelAndLimit(secondCategoryIds, NewBeeMallCategoryLevel.LEVEL_THREE.level, 0)
                val thirdCategoryMap = thirdCategory.groupBy { it.parentId }

                val secondCategoryVoList = mutableListOf<MallIndexCategoryVo>()
                for (mallMerchandiseCategory in secondMerchandiseCategory) {
                    val secondCategoryVo = MallIndexCategoryVo()
                    BeanUtils.copyProperties(mallMerchandiseCategory, secondCategoryVo)
                    val list = thirdCategoryMap[mallMerchandiseCategory.id]
                    secondCategoryVo.childCategory = list?.copyList(MallIndexCategoryVo::class.java)
                    secondCategoryVoList.add(secondCategoryVo)
                }

                if (secondCategoryVoList.isNotEmpty()) {
                    val secondCategoryVoMap = secondCategoryVoList.groupBy { it.parentId }
                    for (mallMerchandiseCategory in merchandiseCategoryList) {
                        val mallIndexCategoryVo = MallIndexCategoryVo()
                        BeanUtils.copyProperties(mallMerchandiseCategory, mallIndexCategoryVo)
                        mallIndexCategoryVo.childCategory = secondCategoryVoMap[mallMerchandiseCategory.id]
                        merchandiseCategoryVoList.add(mallIndexCategoryVo)
                    }
                }
            }
        }
        return merchandiseCategoryVoList
    }
}
