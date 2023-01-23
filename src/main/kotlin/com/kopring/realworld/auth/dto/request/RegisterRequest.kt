package com.kopring.realworld.auth.dto.request

import com.kopring.realworld.auth.db.entity.Member

class RegisterRequest(val member: RegisterMember)
class RegisterMember(val email: String, val password: String, val userName: String){
    fun toEntity(): Member {
        return Member(email,password, userName)
    }
}
