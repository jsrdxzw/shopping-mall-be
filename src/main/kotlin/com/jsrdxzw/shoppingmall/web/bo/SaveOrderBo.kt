package com.jsrdxzw.shoppingmall.web.bo

import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty

/**
 * @author  xuzhiwei
 * @date  2020/09/20
 */
data class SaveOrderBo(
        @ApiModelProperty("购物车id数组")
        @field:NotEmpty(message = "购物车id不能为空")
        val cartItemIds: LongArray,

        @ApiModelProperty("地址id")
        @field:Min(1, message = "用户地址id错误")
        val addressId: Long
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SaveOrderBo

        if (!cartItemIds.contentEquals(other.cartItemIds)) return false
        if (addressId != other.addressId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = cartItemIds.contentHashCode()
        result = 31 * result + addressId.hashCode()
        return result
    }

}
