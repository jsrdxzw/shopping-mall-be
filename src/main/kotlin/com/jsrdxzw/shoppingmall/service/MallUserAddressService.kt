package com.jsrdxzw.shoppingmall.service

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.jsrdxzw.shoppingmall.entity.MallUserAddress
import com.jsrdxzw.shoppingmall.enums.ServiceResult
import com.jsrdxzw.shoppingmall.enums.YesOrNo
import com.jsrdxzw.shoppingmall.exception.MallException
import com.jsrdxzw.shoppingmall.extension.beanCopy
import com.jsrdxzw.shoppingmall.extension.copyList
import com.jsrdxzw.shoppingmall.extension.lambdaQueryWrapper
import com.jsrdxzw.shoppingmall.mapper.MallUserAddressMapper
import com.jsrdxzw.shoppingmall.web.bo.UserAddressBo
import com.jsrdxzw.shoppingmall.web.vo.MallUserAddressVo
import com.jsrdxzw.shoppingmall.web.vo.MallUserLogin
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class MallUserAddressService : ServiceImpl<MallUserAddressMapper, MallUserAddress>() {
    @Autowired
    private lateinit var mallUserAddressMapper: MallUserAddressMapper

    fun getUserAddress(loginMallUser: MallUserLogin): List<MallUserAddressVo> {
        val queryWrapper = lambdaQueryWrapper<MallUserAddress>()
                .eq(MallUserAddress::userId, loginMallUser.userId)
                .eq(MallUserAddress::isDeleted, YesOrNo.NO.code)
                .orderByDesc(MallUserAddress::id)
                .last("limit $ADDRESS_LIMIT_COUNT")
        val result = mallUserAddressMapper.selectList(queryWrapper)
        return result.copyList(MallUserAddressVo::class.java)
    }

    fun addUserAddress(userAddressBo: UserAddressBo, loginMallUser: MallUserLogin) {
        val mallUserAddress = MallUserAddress()
        BeanUtils.copyProperties(userAddressBo, mallUserAddress)
        mallUserAddress.userId = loginMallUser.userId
        if (mallUserAddress.defaultFlag == YesOrNo.YES.code) {
            val defaultAddress = getDefaultUserAddress(loginMallUser.userId)
            if (defaultAddress != null) {
                defaultAddress.defaultFlag = YesOrNo.NO.code
                defaultAddress.updateTime = LocalDateTime.now()
                val res = mallUserAddressMapper.updateById(defaultAddress)
                if (res < 1) {
                    MallException.fail(ServiceResult.DB_ERROR)
                }
            }
        }
        if (mallUserAddressMapper.insert(mallUserAddress) < 1) MallException.fail(ServiceResult.DB_ERROR)
    }

    fun getUserAddressByAddressId(addressId: Long): MallUserAddressVo? {
        val mallUserAddress: MallUserAddress? = getById(addressId)
        return mallUserAddress?.beanCopy(MallUserAddressVo::class.java)
    }

    fun getDefaultUserAddress(loginMallUser: MallUserLogin) =
            getDefaultUserAddress(loginMallUser.userId)?.beanCopy(MallUserAddressVo::class.java)

    private fun getDefaultUserAddress(userId: Long): MallUserAddress? {
        val queryWrapper = lambdaQueryWrapper<MallUserAddress>()
                .eq(MallUserAddress::userId, userId)
                .eq(MallUserAddress::defaultFlag, YesOrNo.YES.code)
        return mallUserAddressMapper.selectOne(queryWrapper)
    }

    fun deleteUserAddress(addressId: Long, loginMallUser: MallUserLogin) {
        val mallUserAddress: MallUserAddress = getById(addressId) ?: MallException.fail(ServiceResult.DATA_NOT_EXIST)
        if (mallUserAddress.userId != loginMallUser.userId) MallException.fail(ServiceResult.REQUEST_FORBIDEN_ERROR)
        mallUserAddress.isDeleted = YesOrNo.YES.code
        if (mallUserAddressMapper.updateById(mallUserAddress) != 1) {
            MallException.fail(ServiceResult.OPERATE_ERROR)
        }
    }

    private companion object {
        const val ADDRESS_LIMIT_COUNT = 10
    }

}
