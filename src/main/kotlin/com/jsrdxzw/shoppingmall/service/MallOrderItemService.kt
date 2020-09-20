package com.jsrdxzw.shoppingmall.service

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.jsrdxzw.shoppingmall.entity.MallOrderItem
import com.jsrdxzw.shoppingmall.enums.ServiceResult
import com.jsrdxzw.shoppingmall.exception.MallException
import com.jsrdxzw.shoppingmall.extension.lambdaQueryWrapper
import com.jsrdxzw.shoppingmall.mapper.MallOrderItemMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author  xuzhiwei
 * @date  2020/09/20
 */
@Service
class MallOrderItemService : ServiceImpl<MallOrderItemMapper, MallOrderItem>() {
    @Autowired
    private lateinit var mallOrderItemMapper: MallOrderItemMapper

    fun selectByOrderNumber(orderId: Long?): List<MallOrderItem> {
        if (orderId == null) MallException.fail(ServiceResult.PARAM_ERROR)
        val queryWrapper = lambdaQueryWrapper<MallOrderItem>()
                .eq(MallOrderItem::orderId, orderId)
        return mallOrderItemMapper.selectList(queryWrapper)
    }
}
