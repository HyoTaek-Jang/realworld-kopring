package com.kopring.realworld.domain.member.controller

import com.kopring.realworld.domain.member.dto.request.LoginRequest
import com.kopring.realworld.domain.member.dto.request.RegisterRequest
import com.kopring.realworld.domain.member.dto.response.MemberResponse
import com.kopring.realworld.auth.dto.response.UsersDto
import com.kopring.realworld.domain.member.service.AuthService
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
        return BaseResponse(MemberResponse(UsersDto(authService.register(registerRequest.member))), 201)
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): BaseResponse<MemberResponse> {
        val member = authService.login(loginRequest.member)
        val token = jwtTokenProvider.createToken(member.email)
        return BaseResponse(MemberResponse(UsersDto(member, token)), 200)
    }
}
