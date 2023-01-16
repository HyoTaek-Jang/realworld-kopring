package com.kopring.realworld.auth.controller

import com.kopring.realworld.auth.db.entity.Member
import com.kopring.realworld.auth.dto.LoginRequest
import com.kopring.realworld.auth.dto.RegisterRequest
import com.kopring.realworld.auth.service.AuthService
import com.kopring.realworld.global.dto.BaseResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(val authService: AuthService) {

    @PostMapping("/users")
    fun register(@RequestBody registerRequest: RegisterRequest): BaseResponse<Member> {
        return BaseResponse(authService.register(registerRequest.member), null, 201)
    }

    @PostMapping("/api/users/login")
    fun login(@RequestBody loginRequest: LoginRequest): BaseResponse<Member> {
        return BaseResponse(authService.login(loginRequest.member), null, 200)
    }
}
