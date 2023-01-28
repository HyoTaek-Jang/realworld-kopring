package com.kopring.realworld.global.jwt

import com.kopring.realworld.domain.member.db.entity.Member
import com.kopring.realworld.domain.member.service.UserDetailsService
import org.springframework.security.core.context.SecurityContextHolder

class SecurityUtils {
    companion object {
        fun getPrincipalMember(): Member {
            val userDetail = SecurityContextHolder.getContext().authentication.principal as UserDetailsService.UserDetailCustom
            return userDetail.member
        }
    }
}
