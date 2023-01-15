package com.kopring.realworld.auth.service

import com.kopring.realworld.auth.db.entity.Member
import com.kopring.realworld.auth.db.repository.MemberRepository
import com.kopring.realworld.auth.dto.RegisterRequest
import com.kopring.realworld.auth.exception.ExistUserException
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

    private val registerRequest = RegisterRequest(Member("test@test.com", "pwd", "taek"))

    @Test
    @DisplayName("중복 아이디가 없으면 정상 가입된다.")
    fun hasNotExistEmailTest() {
        // given
        `when`(memberRepository.findByEmail(registerRequest.member.email)).thenReturn(null)
        `when`(memberRepository.save(any())).thenReturn(registerRequest.member)

        // then
        assertThat(authService.register(registerRequest)).isNotNull
    }

    @Test
    @DisplayName("중복 아이디가 있으면 exception이 발생한다.")
    fun hasExistEmailTest() {
        // given
        `when`(memberRepository.findByEmail(registerRequest.member.email)).thenReturn(registerRequest.member)

        // then
        assertThatThrownBy { authService.register(registerRequest) }
            .isExactlyInstanceOf(ExistUserException::class.java)
    }
}
