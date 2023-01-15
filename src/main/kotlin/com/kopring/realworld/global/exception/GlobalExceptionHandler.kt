package com.kopring.realworld.global.exception

import com.kopring.realworld.global.dto.BaseResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(BaseException::class)
    fun handleBaseException(baseException: BaseException): BaseResponse<Unit> {
        return BaseResponse(Unit, baseException.msg, baseException.status)
    }
}
