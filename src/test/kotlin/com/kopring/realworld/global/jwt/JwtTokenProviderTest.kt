package com.kopring.realworld.global.jwt

import com.kopring.realworld.domain.member.service.UserDetailsService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.security.Key

@ExtendWith(MockitoExtension::class)
internal class JwtTokenProviderTest {
    @Mock
    lateinit var userDetailsService: UserDetailsService
    @InjectMocks
    lateinit var jwtTokenProvider: JwtTokenProvider

    private val secretKey = "TESTKEYAaksjdfklasj123819032812uofsdjakflasdf"
    private val key: Key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))

    @Test
    fun tokenValidTest() {
        val signatureErrorToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QGdtYWlsLmNvbSIsImVtYWlsIjoidGVzdEBnbWFpbC5jb20iLCJpYXQiOjE2NzQ4NzE1OTksImV4cCI6MTcwNzI3MTU5OX0.KZmAoY-J0U_H3SMaHg30Zaae5-9ZuiPxFgUHwFmD5zA"

        assertThatThrownBy { Jwts.parserBuilder().setSigningKey(key).build().parse(signatureErrorToken).body as Claims }
            .isExactlyInstanceOf(SignatureException::class.java)
    }

    @Test
    fun tokenExpiredTest() {
        val expiredToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QGdtYWlsLmNvbSIsImVtYWlsIjoidGVzdEBnbWFpbC5jb20iLCJpYXQiOjE2NzQ4NzIyMTIsImV4cCI6MTY3NDg3MjIxNH0.UDtV-7VhhfPXAh5EMnWIwh6U3-5JK4r3hJRoZ-YIKiU"

        assertThat(jwtTokenProvider.validateToken(expiredToken)).isFalse
    }
}
