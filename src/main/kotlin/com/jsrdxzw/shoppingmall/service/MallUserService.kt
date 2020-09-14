package com.jsrdxzw.shoppingmall.service

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.jsrdxzw.shoppingmall.entity.MallUser
import com.jsrdxzw.shoppingmall.entity.MallUserToken
import com.jsrdxzw.shoppingmall.enums.ActiveType
import com.jsrdxzw.shoppingmall.enums.ServiceResult
import com.jsrdxzw.shoppingmall.enums.YesOrNo
import com.jsrdxzw.shoppingmall.exception.MallException
import com.jsrdxzw.shoppingmall.extension.getNewToken
import com.jsrdxzw.shoppingmall.extension.lambdaQueryWrapper
import com.jsrdxzw.shoppingmall.mapper.MallUserMapper
import com.jsrdxzw.shoppingmall.mapper.MallUserTokenMapper
import com.jsrdxzw.shoppingmall.web.bo.MallUserLoginBo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

/**
 * @author  xuzhiwei
 * @date  2020/08/31
 */
@Service
class MallUserService : ServiceImpl<MallUserMapper, MallUser>() {
    @Autowired
    private lateinit var mallUserMapper: MallUserMapper

    @Autowired
    private lateinit var mallUserTokenMapper: MallUserTokenMapper

    fun login(mallUserLoginBo: MallUserLoginBo): String? {
        val queryWrapper = lambdaQueryWrapper<MallUser>()
                .eq(MallUser::loginName, mallUserLoginBo.loginName)
                .eq(MallUser::passwordMd5, mallUserLoginBo.passwordMd5)
                .eq(MallUser::isDeleted, YesOrNo.NO.code)
        val mallUser: MallUser = mallUserMapper.selectOne(queryWrapper)
                ?: throw MallException(ServiceResult.LOGIN_ERROR.result)

        if (mallUser.lockedFlag == YesOrNo.YES.code) {
            throw MallException(ServiceResult.LOGIN_USER_LOCKED_ERROR.result)
        }

        val token = getNewToken("${System.currentTimeMillis()}", mallUser.id)
        val mallUserToken = mallUserTokenMapper.selectOne(lambdaQueryWrapper<MallUserToken>().eq(MallUserToken::userId, mallUser.id))
        val now = LocalDateTime.now()
        val expireTime = now.plusHours(expireHour)
        if (mallUserToken == null) {
            val userToken = MallUserToken().also {
                it.userId = mallUser.id
                it.token = token
                it.updateTime = now
                it.expireTime = expireTime
            }
            if (mallUserTokenMapper.insert(userToken) > 0) {
                return token
            }
        } else {
            mallUserToken.also {
                it.token = token
                it.updateTime = now
                it.expireTime = expireTime
            }
            if (mallUserTokenMapper.updateById(mallUserToken) > 0) {
                return token
            }
        }
        throw MallException(ServiceResult.LOGIN_ERROR.result)
    }

    companion object {
        private const val expireHour = 48L
    }

}
