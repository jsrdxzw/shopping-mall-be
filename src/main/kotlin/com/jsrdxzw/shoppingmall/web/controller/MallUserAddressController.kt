package com.jsrdxzw.shoppingmall.web.controller

import com.jsrdxzw.shoppingmall.annotation.TokenToMallUser
import com.jsrdxzw.shoppingmall.entity.MallUser
import com.jsrdxzw.shoppingmall.service.MallUserAddressService
import com.jsrdxzw.shoppingmall.util.ResultData
import com.jsrdxzw.shoppingmall.web.bo.UserAddressBo
import com.jsrdxzw.shoppingmall.web.vo.MallUserAddressVo
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * @author  xuzhiwei
 * @date  2020/09/16
 */
@RestController
@RequestMapping("/address")
@Api(tags = ["用户地址接口"])
@Validated
class MallUserAddressController {
    @Autowired
    private lateinit var mallUserAddressService: MallUserAddressService

    @GetMapping
    @ApiOperation(value = "我的收货地址列表")
    fun addressList(@TokenToMallUser loginMallUser: MallUser): ResultData<List<MallUserAddressVo>> {
        return ResultData.success(mallUserAddressService.getUserAddress(loginMallUser))
    }

    @PostMapping
    @ApiOperation(value = "添加收获地址")
    fun addAddress(@Valid @RequestBody userAddressBo: UserAddressBo, @TokenToMallUser loginMallUser: MallUser): ResultData<*> {
        mallUserAddressService.addUserAddress(userAddressBo, loginMallUser)
        return ResultData.SUCCESS
    }

    @GetMapping("/{addressId}")
    @ApiOperation(value = "获取收获地址")
    fun getMallUserAddress(@PathVariable("addressId") addressId: Long, @TokenToMallUser loginMallUser: MallUser): ResultData<MallUserAddressVo?> {
        return ResultData.success(mallUserAddressService.getUserAddressByAddressId(addressId))
    }

    @GetMapping("/default")
    @ApiOperation(value = "获取默认收获地址")
    fun getDefaultMallUserAddress(@TokenToMallUser loginMallUser: MallUser): ResultData<MallUserAddressVo?> {
        return ResultData.success(mallUserAddressService.getDefaultUserAddress(loginMallUser))
    }

    @DeleteMapping("/{addressId}")
    @ApiOperation(value = "删除收获地址")
    fun deleteMallUserAddress(@PathVariable("addressId") addressId: Long, @TokenToMallUser loginMallUser: MallUser): ResultData<*> {
        mallUserAddressService.deleteUserAddress(addressId, loginMallUser)
        return ResultData.SUCCESS
    }
}
