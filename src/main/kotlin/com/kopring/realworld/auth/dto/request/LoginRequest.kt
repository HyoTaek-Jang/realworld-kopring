package com.kopring.realworld.auth.dto.request

class LoginRequest(val member: LoginMember)
class LoginMember(val email: String, val password: String)
