package com.kopring.realworld.auth.dto.response

class MemberResponse(val member: Member) {
    class Member(private val memberEntity: com.kopring.realworld.auth.db.entity.Member, val token: String){
        val email: String = memberEntity.email
        val userName: String = memberEntity.userName
        val bio: String? = null
        val image: String? = null
    }
}
