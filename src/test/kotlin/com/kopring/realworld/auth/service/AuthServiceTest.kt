package com.kopring.realworld.auth.service

import com.kopring.realworld.domain.auth.db.entity.Member
import com.kopring.realworld.domain.auth.db.repository.MemberRepository
import com.kopring.realworld.domain.auth.dto.request.RegisterMember
import com.kopring.realworld.domain.auth.dto.request.RegisterRequest
import com.kopring.realworld.domain.auth.exception.ExistUserException
import com.kopring.realworld.domain.auth.service.AuthService
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class AuthServiceTest{
    @Mock
    lateinit var memberRepository: MemberRepository
    @InjectMocks
    lateinit var authService: AuthService

    private val registerRequest = RegisterRequest(RegisterMember("test@test.com", "pwd", "taek"))
    private val member = registerRequest.member

    @Test
    @DisplayName("중복 아이디가 없으면 정상 가입된다.")
    fun hasNotExistEmailTest() {
        // given
        `when`(memberRepository.findByEmail(member.email)).thenReturn(null)
        `when`(memberRepository.save(any())).thenReturn(Member(member.email, member.password, member.userName))

        // then
        assertThat(authService.register(registerRequest.member)).isNotNull
    }

    @Test
    @DisplayName("중복 아이디가 있으면 exception이 발생한다.")
    fun hasExistEmailTest() {
        // given
        `when`(memberRepository.findByEmail(registerRequest.member.email)).thenReturn(Member(member.email, member.password, member.userName))

        // then
        assertThatThrownBy { authService.register(registerRequest.member) }
            .isExactlyInstanceOf(ExistUserException::class.java)
    }
}
