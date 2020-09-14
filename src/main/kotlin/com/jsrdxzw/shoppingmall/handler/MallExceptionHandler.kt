package com.jsrdxzw.shoppingmall.handler

import com.jsrdxzw.shoppingmall.exception.MallException
import com.jsrdxzw.shoppingmall.util.ResultData
import org.slf4j.LoggerFactory
import org.springframework.validation.BindException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException

/**
 * @author  xuzhiwei
 * @date  2020/08/29
 */
@RestControllerAdvice
class MallExceptionHandler {
    private val logger = LoggerFactory.getLogger(MallExceptionHandler::class.java)

    @ExceptionHandler(BindException::class)
    fun bindException(exception: BindException): ResultData<String?> {
        logger.error("数据校验异常:${exception}")
        return ResultData.error(resultCode = ResultData.RESULT_CODE_INPUT_ERROR, data = exception.bindingResult.fieldError?.defaultMessage)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(exception: MethodArgumentNotValidException): ResultData<String?> {
        logger.error("数据校验异常:${exception}")
        return ResultData.error(resultCode = ResultData.RESULT_CODE_INPUT_ERROR, data = exception.bindingResult.fieldError?.defaultMessage)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun constraintViolationException(exception: ConstraintViolationException): ResultData<String?> {
        logger.error("数据校验异常:${exception}")
        return ResultData.error(resultCode = ResultData.RESULT_CODE_INPUT_ERROR, data = exception.message)
    }

    @ExceptionHandler(MallException::class)
    fun mallExceptionHandle(exception: MallException): ResultData<String?> {
        logger.error("自定义异常:${exception}")
        return ResultData.error(data = exception.message)
    }

}
