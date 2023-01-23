package com.kopring.realworld.domain.auth.controller

import com.kopring.realworld.domain.auth.dto.request.LoginRequest
import com.kopring.realworld.domain.auth.dto.request.RegisterRequest
import com.kopring.realworld.domain.auth.dto.response.MemberResponse
import com.kopring.realworld.auth.dto.response.UsersResponse
import com.kopring.realworld.domain.auth.service.AuthService
import com.kopring.realworld.global.dto.BaseResponse
import com.kopring.realworld.global.jwt.JwtTokenProvider
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class AuthController(val authService: AuthService, val jwtTokenProvider: JwtTokenProvider) {

    @PostMapping
    fun register(@RequestBody registerRequest: RegisterRequest): BaseResponse<MemberResponse> {
        return BaseResponse(MemberResponse(UsersResponse(authService.register(registerRequest.member))), 201)
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): BaseResponse<MemberResponse> {
        val member = authService.login(loginRequest.member)
        val token = jwtTokenProvider.createToken(member.email)
        return BaseResponse(MemberResponse(UsersResponse(member, token)), 200)
    }
}