package com.kopring.realworld.domain.member.service

import com.kopring.realworld.auth.dto.response.ProfileResponse
import com.kopring.realworld.domain.member.db.entity.Follow
import com.kopring.realworld.domain.member.db.repository.FollowRepository
import com.kopring.realworld.domain.member.db.repository.MemberRepository
import com.kopring.realworld.domain.member.exception.NotExistUserException
import com.kopring.realworld.global.jwt.SecurityUtils.Companion.getPrincipalMember
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProfilesService(val memberRepository: MemberRepository, val followRepository: FollowRepository) {
    fun getProfile(userName: String): ProfileResponse {
        val targetMember = memberRepository.findByUserName(userName) ?: throw NotExistUserException()
        // 내가 상대를 follow 하는지 확인
        val isFollowing = followRepository.findByFromAndTo(getPrincipalMember(), targetMember) != null

        return ProfileResponse(targetMember, isFollowing)
    }

    fun followMember(userName: String): ProfileResponse {
        val targetMember = memberRepository.findByUserName(userName) ?: throw NotExistUserException()
        followRepository.save(Follow(getPrincipalMember(), targetMember))
        return ProfileResponse(targetMember, true)
    }

    @Transactional
    fun unfollowMember(userName: String): ProfileResponse {
        val targetMember = memberRepository.findByUserName(userName) ?: throw NotExistUserException()
        followRepository.deleteByFromAndTo(getPrincipalMember(), targetMember)
        return ProfileResponse(targetMember, false)
    }
}
