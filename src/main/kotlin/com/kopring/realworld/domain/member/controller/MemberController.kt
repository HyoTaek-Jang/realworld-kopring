package com.kopring.realworld.domain.member.controller

import com.kopring.realworld.auth.dto.response.UsersDto
import com.kopring.realworld.domain.member.dto.request.UpdateMemberRequest
import com.kopring.realworld.domain.member.dto.response.MemberResponse
import com.kopring.realworld.domain.member.service.MemberService
import com.kopring.realworld.global.dto.BaseResponse
import com.kopring.realworld.global.jwt.SecurityUtils.Companion.getPrincipalMember
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class MemberController(val memberService: MemberService) {

    @GetMapping
    fun getCurrentMember(): BaseResponse<MemberResponse> {
        return BaseResponse(MemberResponse(UsersDto(getPrincipalMember())), 200)
    }

    @PutMapping
    fun updateMember(@RequestBody updateMemberRequest: UpdateMemberRequest): BaseResponse<MemberResponse> {
        return BaseResponse(MemberResponse(UsersDto(memberService.updateMember(updateMemberRequest))), 201)
    }
}
