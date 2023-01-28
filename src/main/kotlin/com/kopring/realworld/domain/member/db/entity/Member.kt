package com.kopring.realworld.domain.member.db.entity

import com.kopring.realworld.domain.member.dto.request.UpdateMember
import com.kopring.realworld.global.audit.AuditBase
import javax.persistence.*

@Entity
class Member(
    @Column(nullable = false, unique = true) var email: String,
    @Column(nullable = false) var password: String,
    @Column(nullable = false, unique = true) var userName: String,
    var bio: String?,
    var image: String?
) :
    AuditBase() {
    fun update(updateMember: UpdateMember) {
        this.email = updateMember.email ?: this.email
        this.password = updateMember.password ?: this.password
        this.bio = updateMember.bio ?: this.bio
        this.image = updateMember.image ?: this.image
        this.userName = updateMember.userName ?: this.userName
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
