package com.kopring.realworld.global.security

import com.kopring.realworld.global.jwt.JwtAuthenticationFilter
import com.kopring.realworld.global.jwt.JwtTokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
@Configuration
class SecurityConfig(private val jwtTokenProvider: JwtTokenProvider) {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {
        return http.
        httpBasic().disable() // rest api만 고려, 기본 설정 해제
            .csrf().disable() // csrf 보안 토큰 disable 처리
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 토큰 기반 인증이므로 세션 사용 안함
            .and()
            .authorizeRequests() // 요청에 대한 사용권한 체크
            .antMatchers("/api/users/**").permitAll() // 로그인, 회원가입은 누구나 접근 가능
            .antMatchers("/api/**").authenticated()
            .and()
            .addFilterBefore(JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }
}
