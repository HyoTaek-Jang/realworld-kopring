package com.kopring.realworld.global.dto

class BaseResponse<T>(val body: T, val message: String?, val code: Int)
