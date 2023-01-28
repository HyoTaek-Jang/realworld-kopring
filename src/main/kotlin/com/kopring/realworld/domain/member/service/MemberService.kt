package com.kopring.realworld.domain.member.service

import com.kopring.realworld.domain.member.db.entity.Member
import com.kopring.realworld.domain.member.db.repository.MemberRepository
import com.kopring.realworld.domain.member.dto.request.UpdateMemberRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class MemberService(val memberRepository: MemberRepository) {
    fun getCurrentMember(): Member {
        val userDetail = SecurityContextHolder.getContext().authentication.principal as UserDetailsService.UserDetailCustom
        return userDetail.member
    }

    fun updateMember(updateMemberRequest: UpdateMemberRequest): Member {
        val userDetail = SecurityContextHolder.getContext().authentication.principal as UserDetailsService.UserDetailCustom
        val member = userDetail.member
        member.update(updateMemberRequest.user)
        return memberRepository.save(member)
    }
}
