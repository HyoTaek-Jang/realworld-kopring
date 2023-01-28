package com.kopring.realworld.domain.member.dto.request

class UpdateMemberRequest(val user: UpdateMember)

class UpdateMember(val email: String?, val bio: String?, val image: String?, val userName: String?, val password: String?)
