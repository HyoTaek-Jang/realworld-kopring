package com.kopring.realworld.global.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*
import javax.servlet.http.HttpServletRequest

@Component
class JwtTokenProvider(private val userDetailsService: UserDetailsService) {
    private val secretKey = "TESTKEYAaksjdfklasj123819032812uofsdjakflasdf"
    // 단위는 ms
    private val tokenValidTime = 30 * 60 * 1000L * 60 * 30
    private val key: Key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))

    fun createToken(email: String): String {
        val claims: Claims = Jwts.claims().setSubject(email)
        claims["email"] = email
        val date = Date()
        return Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .setClaims(claims)
            .setIssuedAt(date)
            .setExpiration(Date(date.time + tokenValidTime))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun getEmail(token: String): String {
        val parser = Jwts.parserBuilder().setSigningKey(key).build()
        val claims = parser.parse(token).body as Claims
        return claims.subject
    }

    fun getAuthentication(token: String): Authentication {
        val userDetails = userDetailsService.loadUserByUsername(getEmail(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    // Request의 Header에서 token 값을 가져옵니다. "X-AUTH-TOKEN" : "TOKEN값'
    fun resolveToken(request: HttpServletRequest): String? {
        return request.getHeader("Authorization")
    }

    // 토큰의 유효성 + 만료일자 확인
    fun validateToken(jwtToken: String): Boolean {
        return try {
            val body: Claims = Jwts.parserBuilder().setSigningKey(key).build().parse(jwtToken).body as Claims
            return !body.expiration.before(Date()) // return 생략
        } catch (e: Exception) {
            false // return 생략
        }
    }
}
