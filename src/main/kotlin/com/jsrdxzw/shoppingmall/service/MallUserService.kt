package com.jsrdxzw.shoppingmall.service

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.jsrdxzw.shoppingmall.common.Constants
import com.jsrdxzw.shoppingmall.entity.MallUser
import com.jsrdxzw.shoppingmall.entity.MallUserToken
import com.jsrdxzw.shoppingmall.enums.ServiceResult
import com.jsrdxzw.shoppingmall.enums.YesOrNo
import com.jsrdxzw.shoppingmall.exception.MallException
import com.jsrdxzw.shoppingmall.extension.getNewToken
import com.jsrdxzw.shoppingmall.extension.lambdaQueryWrapper
import com.jsrdxzw.shoppingmall.extension.md5
import com.jsrdxzw.shoppingmall.mapper.MallUserMapper
import com.jsrdxzw.shoppingmall.mapper.MallUserTokenMapper
import com.jsrdxzw.shoppingmall.web.bo.MallUserLoginBo
import com.jsrdxzw.shoppingmall.web.bo.MallUserRegisterBo
import com.jsrdxzw.shoppingmall.web.bo.MallUserUpdateBo
import com.jsrdxzw.shoppingmall.web.vo.MallUserLogin
import com.jsrdxzw.shoppingmall.web.vo.MallUserVo
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
                ?: throw MallException(ServiceResult.LOGIN_ERROR)

        if (mallUser.lockedFlag == YesOrNo.YES.code) {
            throw MallException(ServiceResult.LOGIN_USER_LOCKED_ERROR)
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
        throw MallException(ServiceResult.LOGIN_ERROR)
    }

    fun logout(id: Long?) {
        if (id == null) throw MallException(ServiceResult.LOGOUT_ERROR)
        val result = mallUserTokenMapper.delete(lambdaQueryWrapper<MallUserToken>().eq(MallUserToken::userId, id))
        if (result == 0) throw MallException(ServiceResult.LOGOUT_ERROR)
    }

    fun register(mallUserRegisterBo: MallUserRegisterBo) {
        var mallUser: MallUser? = mallUserMapper.selectOne(lambdaQueryWrapper<MallUser>().eq(MallUser::loginName, mallUserRegisterBo.loginName))
        if (mallUser != null) {
            throw MallException(ServiceResult.SAME_LOGIN_NAME_EXIST)
        }
        mallUser = MallUser().apply {
            loginName = mallUserRegisterBo.loginName
            nickName = mallUserRegisterBo.loginName
            introduceSign = Constants.USER_INTRO
            passwordMd5 = mallUserRegisterBo.password.md5()
        }
        if (mallUserMapper.insert(mallUser) == 0) throw MallException(ServiceResult.DB_ERROR)
    }

    fun updateUserInfo(mallUserUpdateBo: MallUserUpdateBo, mallUser: MallUserLogin) {
        val user: MallUser = mallUserMapper.selectById(mallUser.userId)
                ?: throw MallException(ServiceResult.DATA_NOT_EXIST)
        user.nickName = mallUserUpdateBo.nickName
        user.passwordMd5 = mallUserUpdateBo.passwordMd5
        mallUserUpdateBo.introduceSign?.let { user.introduceSign = it }
        if (mallUserMapper.updateById(user) == 0) {
            throw MallException(ServiceResult.UPDATE_USER_ERROR)
        }
    }

    fun getUserInfo(loginMallUser: MallUserLogin): MallUserVo {
        return MallUserVo(loginMallUser.loginName, loginMallUser.loginName, loginMallUser.introduceSign)
    }

    companion object {
        private const val expireHour = 48L
    }

}
