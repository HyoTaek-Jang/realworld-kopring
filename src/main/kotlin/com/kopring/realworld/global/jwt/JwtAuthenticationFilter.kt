package com.kopring.realworld.global.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import com.kopring.realworld.global.dto.BaseResponse
import com.kopring.realworld.global.exception.BaseException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JwtAuthenticationFilter(private val jwtTokenProvider: JwtTokenProvider): GenericFilterBean() {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        try {
            // jwt 토큰 발급
            val token: String? = jwtTokenProvider.resolveToken((request as HttpServletRequest))
            // 유효한 토큰인지 확인합니다.
            if (token != null && jwtTokenProvider.validateToken(token)) {
                // 토큰이 유효하면 토큰으로부터 유저 정보를 받아옵니다.
                val authentication = jwtTokenProvider.getAuthentication(token)
                // SecurityContext 에 Authentication 객체를 저장합니다.
                SecurityContextHolder.getContext().authentication = authentication
            }
        } catch (exception: BaseException) {
            setErrorResponse(response as HttpServletResponse, exception)
        }
        chain?.doFilter(request, response)
    }

    private fun setErrorResponse(
        response: HttpServletResponse,
        exception: BaseException
    ) {
        val objectMapper = ObjectMapper()
        response.contentType = "application/json;charset=UTF-8"
        val baseException = BaseResponse(exception.msg, exception.status)
        try {
            val writeValueAsString = objectMapper.writeValueAsString(baseException)
            response.writer.write(writeValueAsString)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
