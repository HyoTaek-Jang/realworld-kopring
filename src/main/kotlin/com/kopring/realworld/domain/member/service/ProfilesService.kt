package com.kopring.realworld.domain.member.service

import com.kopring.realworld.auth.dto.response.ProfileDto
import com.kopring.realworld.domain.member.db.entity.Follow
import com.kopring.realworld.domain.member.db.repository.FollowRepository
import com.kopring.realworld.domain.member.db.repository.MemberRepository
import com.kopring.realworld.domain.member.exception.NotExistUserException
import com.kopring.realworld.global.jwt.SecurityUtils.Companion.getPrincipalMember
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProfilesService(val memberRepository: MemberRepository, val followRepository: FollowRepository) {
    fun getProfile(userName: String): ProfileDto {
        val targetMember = memberRepository.findByUserName(userName) ?: throw NotExistUserException()
        // 내가 상대를 follow 하는지 확인
        val isFollowing = followRepository.findByFromAndTo(getPrincipalMember(), targetMember) != null

        return ProfileDto(targetMember, isFollowing)
    }

    fun followMember(userName: String): ProfileDto {
        val targetMember = memberRepository.findByUserName(userName) ?: throw NotExistUserException()
        followRepository.save(Follow(getPrincipalMember(), targetMember))
        return ProfileDto(targetMember, true)
    }

    @Transactional
    fun unfollowMember(userName: String): ProfileDto {
        val targetMember = memberRepository.findByUserName(userName) ?: throw NotExistUserException()
        followRepository.deleteByFromAndTo(getPrincipalMember(), targetMember)
        return ProfileDto(targetMember, false)
    }
}
