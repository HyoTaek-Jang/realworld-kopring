package com.kopring.realworld.auth.dto

class LoginRequest(val member:Member) {
    class Member(val email: String, val password: String)
}
