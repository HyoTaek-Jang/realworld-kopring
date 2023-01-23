package com.kopring.realworld.auth.controller

import com.kopring.realworld.auth.db.entity.Member
import com.kopring.realworld.auth.dto.request.LoginRequest
import com.kopring.realworld.auth.dto.request.RegisterRequest
import com.kopring.realworld.auth.dto.response.MemberResponse
import com.kopring.realworld.auth.service.AuthService
import com.kopring.realworld.global.dto.BaseResponse
import com.kopring.realworld.global.jwt.JwtTokenProvider
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(val authService: AuthService, val jwtTokenProvider: JwtTokenProvider) {

    @PostMapping("/api/users")
    fun register(@RequestBody registerRequest: RegisterRequest): BaseResponse<Member> {
        return BaseResponse(authService.register(registerRequest.member), 201)
    }

    @PostMapping("/api/users/login")
    fun login(@RequestBody loginRequest: LoginRequest): BaseResponse<MemberResponse> {
        val member = authService.login(loginRequest.member)
        val token = jwtTokenProvider.createToken(member.email)
        return BaseResponse(MemberResponse(MemberResponse.Member(member, token)), 200)
    }
}
