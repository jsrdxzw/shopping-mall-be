package com.jsrdxzw.shoppingmall.util

import io.swagger.annotations.ApiModelProperty

/**
 * @author  xuzhiwei
 * @date  2020/08/28
 */
data class ResultData<T>(
        @ApiModelProperty("返回码")
        var resultCode: Int = 0,
        @ApiModelProperty("返回信息")
        var message: String? = null,
        @ApiModelProperty("返回数据")
        var data: T? = null
) {
    companion object {
        private const val DEFAULT_SUCCESS_MESSAGE = "success"
        private const val DEFAULT_FAIL_MESSAGE = "fail"
        private const val RESULT_CODE_SUCCESS = 200
        const val RESULT_CODE_SERVER_ERROR = 500
        const val RESULT_CODE_INPUT_ERROR = 400
        val SUCCESS = ResultData<Any?>(RESULT_CODE_SUCCESS, DEFAULT_SUCCESS_MESSAGE)
        fun <T> success(data: T) = ResultData(RESULT_CODE_SUCCESS, DEFAULT_SUCCESS_MESSAGE, data)
        fun <T> error(resultCode: Int = RESULT_CODE_SERVER_ERROR, data: T) = ResultData(resultCode, DEFAULT_FAIL_MESSAGE, data)
    }
}
