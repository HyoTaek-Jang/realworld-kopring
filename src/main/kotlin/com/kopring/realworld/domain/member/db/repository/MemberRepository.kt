package com.kopring.realworld.domain.member.db.repository

import com.kopring.realworld.domain.member.db.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository: JpaRepository<Member, Long> {
    fun findByEmail(email: String): Member?
}
