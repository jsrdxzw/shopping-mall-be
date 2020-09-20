package com.jsrdxzw.shoppingmall.service

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.jsrdxzw.shoppingmall.entity.MallOrderAddress
import com.jsrdxzw.shoppingmall.mapper.MallOrderAddressMapper
import org.springframework.stereotype.Service

@Service
class MallOrderAddressService : ServiceImpl<MallOrderAddressMapper, MallOrderAddress>() {

}
