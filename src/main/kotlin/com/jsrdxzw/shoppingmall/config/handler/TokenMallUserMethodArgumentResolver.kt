package com.jsrdxzw.shoppingmall.config.handler

import com.jsrdxzw.shoppingmall.annotation.TokenToMallUser
import com.jsrdxzw.shoppingmall.common.Constants
import com.jsrdxzw.shoppingmall.entity.MallUserToken
import com.jsrdxzw.shoppingmall.enums.ServiceResult
import com.jsrdxzw.shoppingmall.exception.MallException
import com.jsrdxzw.shoppingmall.extension.lambdaQueryWrapper
import com.jsrdxzw.shoppingmall.mapper.MallUserMapper
import com.jsrdxzw.shoppingmall.mapper.MallUserTokenMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import java.time.LocalDateTime

/**
 * @author  xuzhiwei
 * @date  2020/08/27
 */
@Component
class TokenMallUserMethodArgumentResolver : HandlerMethodArgumentResolver {

    @Autowired
    lateinit var mallUserMapper: MallUserMapper

    @Autowired
    lateinit var mallUserTokenMapper: MallUserTokenMapper

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(TokenToMallUser::class.java)
    }

    override fun resolveArgument(parameter: MethodParameter, mavContainer: ModelAndViewContainer?, webRequest: NativeWebRequest, binderFactory: WebDataBinderFactory?): Any? {
        return parameter.getParameterAnnotation(TokenToMallUser::class.java)?.let {
            val token = webRequest.getHeader("Authorization")
            if (!token.isNullOrEmpty() && token.length == Constants.TOKEN_LENGTH) {
                val mallUserToken: MallUserToken? = mallUserTokenMapper.selectOne(
                        lambdaQueryWrapper<MallUserToken>().eq(MallUserToken::token, token)
                )
                if (mallUserToken == null || mallUserToken.expireTime?.isBefore(LocalDateTime.now()) != false) {
                    MallException.fail(ServiceResult.TOKEN_EXPIRE_ERROR)
                }
                mallUserMapper.selectById(mallUserToken?.userId)?.let {
                    if (it.lockedFlag == 1) {
                        MallException.fail(ServiceResult.LOGIN_USER_LOCKED_ERROR)
                    }
                    return it
                } ?: MallException.fail(ServiceResult.USER_NULL_ERROR)
            } else {
                MallException.fail(ServiceResult.NOT_LOGIN_ERROR)
            }
        }
    }
}
