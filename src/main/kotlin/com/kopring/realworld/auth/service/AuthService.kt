package com.kopring.realworld.auth.service

import com.kopring.realworld.auth.db.entity.Member
import com.kopring.realworld.auth.db.repository.MemberRepository
import com.kopring.realworld.auth.dto.request.LoginMember
import com.kopring.realworld.auth.dto.request.RegisterMember
import com.kopring.realworld.auth.exception.ExistUserException
import com.kopring.realworld.auth.exception.NotExistUserException
import com.kopring.realworld.auth.exception.NotMatchPasswordException
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
