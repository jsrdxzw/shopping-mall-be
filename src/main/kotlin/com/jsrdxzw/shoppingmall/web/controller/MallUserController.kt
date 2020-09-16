package com.jsrdxzw.shoppingmall.web.controller

import com.jsrdxzw.shoppingmall.annotation.TokenToMallUser
import com.jsrdxzw.shoppingmall.entity.MallUser
import com.jsrdxzw.shoppingmall.extension.getLogger
import com.jsrdxzw.shoppingmall.service.MallUserService
import com.jsrdxzw.shoppingmall.util.ResultData
import com.jsrdxzw.shoppingmall.web.bo.MallUserLoginBo
import com.jsrdxzw.shoppingmall.web.bo.MallUserRegisterBo
import com.jsrdxzw.shoppingmall.web.bo.MallUserUpdateBo
import com.jsrdxzw.shoppingmall.web.vo.MallUserVo
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * @author  xuzhiwei
 * @date  2020/08/31
 */
@RestController
@RequestMapping("/user")
@Api(tags = ["用户接口"])
@Validated
class MallUserController {
    @Autowired
    private lateinit var mallUserService: MallUserService

    @ApiOperation(value = "登录接口", notes = "返回token")
    @PostMapping("/login")
    fun login(@Valid @RequestBody mallUserLoginBo: MallUserLoginBo): ResultData<*> {
        val loginResult = mallUserService.login(mallUserLoginBo)
        return ResultData.success(loginResult)
    }

    @ApiOperation(value = "登出接口", notes = "清除token")
    @PostMapping("/logout")
    fun login(@RequestBody mallUser: MallUser): ResultData<*> {
        mallUserService.logout(mallUser.id)
        return ResultData.SUCCESS
    }

    @ApiOperation(value = "注册接口")
    @PostMapping("/register")
    fun register(@Valid @RequestBody mallUserRegisterBo: MallUserRegisterBo): ResultData<*> {
        val registerResult = mallUserService.register(mallUserRegisterBo)
        return ResultData.success(registerResult)
    }

    @ApiOperation(value = "修改接口")
    @PutMapping("/update")
    fun update(@Valid @RequestBody mallUserUpdateBo: MallUserUpdateBo, @TokenToMallUser mallUser: MallUser): ResultData<*> {
        mallUserService.updateUserInfo(mallUserUpdateBo, mallUser)
        return ResultData.SUCCESS
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/info")
    fun getUserDetail(@TokenToMallUser loginMallUser: MallUser): ResultData<MallUserVo> {
        return ResultData.success(mallUserService.getUserInfo(loginMallUser))
    }

    companion object {
        private val logger = getLogger<MallUserController>()
    }
}
