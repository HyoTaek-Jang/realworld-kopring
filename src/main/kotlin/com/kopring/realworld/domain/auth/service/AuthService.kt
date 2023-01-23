package com.kopring.realworld.domain.auth.service

import com.kopring.realworld.domain.auth.db.entity.Member
import com.kopring.realworld.domain.auth.db.repository.MemberRepository
import com.kopring.realworld.domain.auth.dto.request.LoginMember
import com.kopring.realworld.domain.auth.dto.request.RegisterMember
import com.kopring.realworld.domain.auth.exception.ExistUserException
import com.kopring.realworld.domain.auth.exception.NotExistUserException
import com.kopring.realworld.domain.auth.exception.NotMatchPasswordException
import org.springframework.stereotype.Service

@Service
class AuthService(val memberRepository: MemberRepository) {
    fun register(member: RegisterMember): Member {
        if (isExistUser(member.email)) throw ExistUserException()
        return memberRepository.save(member.toEntity())
    }

    private fun isExistUser(email: String): Boolean {
        return memberRepository.findByEmail(email) != null
    }

    fun login(loginRequest: LoginMember): Member {
        val member = memberRepository.findByEmail(loginRequest.email) ?: throw NotExistUserException()
        if (member.password != loginRequest.password) throw NotMatchPasswordException()
        return member
    }
}
