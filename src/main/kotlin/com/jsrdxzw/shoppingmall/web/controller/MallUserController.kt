package com.jsrdxzw.shoppingmall.web.controller

import com.jsrdxzw.shoppingmall.extension.getLogger
import com.jsrdxzw.shoppingmall.service.MallUserService
import com.jsrdxzw.shoppingmall.util.ResultData
import com.jsrdxzw.shoppingmall.web.bo.MallUserLoginBo
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
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

    companion object {
        private val logger = getLogger<MallUserController>()
    }
}
