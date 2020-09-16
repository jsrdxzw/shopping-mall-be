package com.jsrdxzw.shoppingmall.exception

import com.jsrdxzw.shoppingmall.enums.ServiceResult

/**
 * @author  xuzhiwei
 * @date  2020/08/26
 */
class MallException : RuntimeException {
    constructor() : super()

    constructor(message: String) : super(message)

    constructor(serviceError: ServiceResult) : super(serviceError.result)

    companion object {
        fun fail(message: String) {
            throw MallException(message)
        }

        fun fail(serviceError: ServiceResult) {
            throw MallException(serviceError)
        }
    }
}
