package com.kopring.realworld.domain.member.dto.request

class LoginRequest(val member: LoginMember)
class LoginMember(val email: String, val password: String)
