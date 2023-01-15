package com.kopring.realworld.auth.service

import com.kopring.realworld.auth.db.entity.Member
import com.kopring.realworld.auth.db.repository.MemberRepository
import com.kopring.realworld.auth.dto.RegisterRequest
import com.kopring.realworld.auth.exception.ExistUserException
import org.springframework.stereotype.Service

@Service
class AuthService(val memberRepository: MemberRepository) {
    fun register(registerRequest: RegisterRequest): Member {
        if (isExistUser(registerRequest.member.email)) throw ExistUserException()
        return memberRepository.save(registerRequest.member)
    }

    private fun isExistUser(email: String): Boolean {
        return memberRepository.findByEmail(email) != null
    }
}
