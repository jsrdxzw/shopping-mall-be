package com.jsrdxzw.shoppingmall.service

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.jsrdxzw.shoppingmall.entity.MallUser
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
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class MallUserAddressService : ServiceImpl<MallUserAddressMapper, MallUserAddress>() {
    @Autowired
    private lateinit var mallUserAddressMapper: MallUserAddressMapper

    fun getUserAddress(loginMallUser: MallUser): List<MallUserAddressVo> {
        val queryWrapper = lambdaQueryWrapper<MallUserAddress>()
                .eq(MallUserAddress::userId, loginMallUser.id)
                .eq(MallUserAddress::isDeleted, YesOrNo.NO.code)
                .orderByDesc(MallUserAddress::id)
                .last("limit $ADDRESS_LIMIT_COUNT")
        val result = mallUserAddressMapper.selectList(queryWrapper)
        return result.copyList(MallUserAddressVo::class.java)
    }

    fun addUserAddress(userAddressBo: UserAddressBo, loginMallUser: MallUser) {
        val mallUserAddress = MallUserAddress()
        BeanUtils.copyProperties(userAddressBo, mallUserAddress)
        mallUserAddress.userId = loginMallUser.id
        if (mallUserAddress.defaultFlag == YesOrNo.YES.code) {
            val defaultAddress = getDefaultUserAddress(loginMallUser.id!!)
            if (defaultAddress != null) {
                defaultAddress.defaultFlag = YesOrNo.NO.code
                defaultAddress.updateTime = LocalDateTime.now()
                val res = mallUserAddressMapper.updateById(defaultAddress)
                if (res < 1) {
                    throw MallException(ServiceResult.DB_ERROR)
                }
            }
        }
        if (mallUserAddressMapper.insert(mallUserAddress) < 1) throw MallException(ServiceResult.DB_ERROR)
    }

    fun getUserAddressByAddressId(addressId: Long): MallUserAddressVo? {
        val mallUserAddress: MallUserAddress? = getById(addressId)
        return mallUserAddress?.beanCopy(MallUserAddressVo::class.java)
    }

    fun getDefaultUserAddress(loginMallUser: MallUser) =
            getDefaultUserAddress(loginMallUser.id!!)?.beanCopy(MallUserAddressVo::class.java)

    private fun getDefaultUserAddress(userId: Long): MallUserAddress? {
        val queryWrapper = lambdaQueryWrapper<MallUserAddress>()
                .eq(MallUserAddress::userId, userId)
                .eq(MallUserAddress::defaultFlag, YesOrNo.YES.code)
        return mallUserAddressMapper.selectOne(queryWrapper)
    }

    fun deleteUserAddress(addressId: Long, loginMallUser: MallUser) {
        val mallUserAddress: MallUserAddress = getById(addressId) ?: throw MallException(ServiceResult.DATA_NOT_EXIST)
        if (mallUserAddress.userId != loginMallUser.id) throw MallException(ServiceResult.REQUEST_FORBIDEN_ERROR)
        mallUserAddress.isDeleted = YesOrNo.YES.code
        if (mallUserAddressMapper.updateById(mallUserAddress) != 1) {
            throw MallException(ServiceResult.OPERATE_ERROR)
        }
    }

    private companion object {
        const val ADDRESS_LIMIT_COUNT = 10
    }

}