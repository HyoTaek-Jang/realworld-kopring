package com.kopring.realworld.domain.member.db.repository

import com.kopring.realworld.domain.member.db.entity.Follow
import com.kopring.realworld.domain.member.db.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FollowRepository : JpaRepository<Follow, Long> {
    fun findByFromAndTo(from: Member, to: Member): Follow?
    fun deleteByFromAndTo(principalMember: Member, targetMember: Member): Int
}
