package com.jsrdxzw.shoppingmall.exception

/**
 * @author  xuzhiwei
 * @date  2020/08/26
 */
class NewBeeMallException : RuntimeException {
    constructor() : super()

    constructor(message: String) : super(message)

    companion object {
        fun fail(message: String) {
            throw NewBeeMallException(message)
        }
    }
}
