package com.kopring.realworld.auth.dto.request

class LoginRequest(val member: Member) {
    class Member(val email: String, val password: String)
}
