package com.kopring.realworld.domain.member.controller

import com.kopring.realworld.auth.dto.response.UsersResponse
import com.kopring.realworld.domain.member.dto.response.MemberResponse
import com.kopring.realworld.domain.member.service.UserDetailsService
import com.kopring.realworld.global.dto.BaseResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class MemberController {

    @GetMapping
    fun getCurrentMember(): BaseResponse<MemberResponse> {
        val userDetail = SecurityContextHolder.getContext().authentication.principal as UserDetailsService.UserDetailCustom
        val member = userDetail.member
        return BaseResponse(MemberResponse(UsersResponse(member)), 200)
    }
}
