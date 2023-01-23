package com.kopring.realworld.global.exception

import com.kopring.realworld.global.dto.BaseResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.Exception

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(BaseException::class)
    fun handleBaseException(baseException: BaseException): BaseResponse<String> {
        return BaseResponse(baseException.msg, baseException.status)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): BaseResponse<String> {
        return BaseResponse(e.message!!, 500)
    }
}
