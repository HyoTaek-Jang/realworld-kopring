package com.kopring.realworld.domain.member.service

import com.kopring.realworld.auth.dto.response.ProfileResponse
import com.kopring.realworld.domain.member.db.repository.FollowRepository
import com.kopring.realworld.domain.member.db.repository.MemberRepository
import com.kopring.realworld.domain.member.exception.NotExistUserException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class ProfilesService(val memberRepository: MemberRepository, val followRepository: FollowRepository) {
    fun getProfile(userName: String): ProfileResponse {
        val userDetail = SecurityContextHolder.getContext().authentication.principal as UserDetailsService.UserDetailCustom

        val targetMember = memberRepository.findByUserName(userName) ?: throw NotExistUserException()
        // 내가 상대를 follow 하는지 확인
        val isFollowing = followRepository.findByFromAndTo(userDetail.member, targetMember) != null

        return ProfileResponse(targetMember, isFollowing)
    }
}
