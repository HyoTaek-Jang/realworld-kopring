package com.kopring.realworld.domain.member.service

import com.kopring.realworld.domain.member.db.entity.Member
import com.kopring.realworld.domain.member.db.repository.MemberRepository
import com.kopring.realworld.domain.member.exception.NotExistUserException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsService(val memberRepository: MemberRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val member = memberRepository.findByEmail(username) ?: throw NotExistUserException()
        return UserDetailCustom(member)
    }

    class UserDetailCustom(val member: Member) : UserDetails {
        private var enabled: Boolean = true

        override fun getAuthorities(): MutableCollection<out GrantedAuthority> = AuthorityUtils.createAuthorityList()

        override fun getPassword(): String = member.password

        override fun getUsername(): String = member.email

        override fun isAccountNonExpired(): Boolean = enabled

        override fun isAccountNonLocked(): Boolean = enabled

        override fun isCredentialsNonExpired(): Boolean = enabled

        override fun isEnabled(): Boolean = enabled
    }
}
