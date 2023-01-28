package com.kopring.realworld.domain.member.service

import com.kopring.realworld.domain.member.db.entity.Member
import com.kopring.realworld.domain.member.db.repository.MemberRepository
import com.kopring.realworld.domain.member.dto.request.UpdateMemberRequest
import com.kopring.realworld.global.jwt.SecurityUtils.Companion.getPrincipalMember
import org.springframework.stereotype.Service

@Service
class MemberService(val memberRepository: MemberRepository) {
    fun updateMember(updateMemberRequest: UpdateMemberRequest): Member {
        val member = getPrincipalMember()
        member.update(updateMemberRequest.user)
        return memberRepository.save(member)
    }
}
