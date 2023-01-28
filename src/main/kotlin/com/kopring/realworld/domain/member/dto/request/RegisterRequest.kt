package com.kopring.realworld.domain.member.dto.request

import com.kopring.realworld.domain.member.db.entity.Member

class RegisterRequest(val member: RegisterMember)
class RegisterMember(val email: String, val password: String, val userName: String){
    fun toEntity(): Member {
        return Member(email,password, userName, null, null)
    }
}
