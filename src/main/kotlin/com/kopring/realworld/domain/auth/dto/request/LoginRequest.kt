package com.kopring.realworld.domain.auth.dto.request

class LoginRequest(val member: LoginMember)
class LoginMember(val email: String, val password: String)
