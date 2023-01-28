package com.kopring.realworld.domain.member.controller

import com.kopring.realworld.auth.dto.response.ProfileResponse
import com.kopring.realworld.domain.member.service.ProfilesService
import com.kopring.realworld.global.dto.BaseResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/profiles")
class ProfilesController(val profilesService: ProfilesService) {

    @GetMapping("/{userName}")
    fun getProfiles(@PathVariable userName: String): BaseResponse<ProfileResponse> {
        return BaseResponse(profilesService.getProfile(userName), 200)
    }
}
