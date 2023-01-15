package com.kopring.realworld.auth.controller

import com.kopring.realworld.auth.dto.RegisterRequest
import com.kopring.realworld.auth.service.AuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(val authService: AuthService) {

    @PostMapping("/users")
    fun register(@RequestBody registerRequest: RegisterRequest) {
        authService.register(registerRequest)
    }
}
