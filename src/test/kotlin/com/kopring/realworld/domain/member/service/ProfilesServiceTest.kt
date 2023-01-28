package com.kopring.realworld.domain.member.service

import com.kopring.realworld.domain.member.db.repository.FollowRepository
import com.kopring.realworld.domain.member.db.repository.MemberRepository
import com.kopring.realworld.domain.member.exception.NotExistUserException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class ProfilesServiceTest {
    @Mock
    lateinit var memberRepository: MemberRepository

    @Mock
    lateinit var followRepository: FollowRepository

    @InjectMocks
    lateinit var profilesService: ProfilesService

    @Test
    fun notExistUserTest() {
        given(memberRepository.findByUserName("test")).willReturn(null)
        assertThrows(NotExistUserException::class.java) { profilesService.getProfile("test") }
    }
}
