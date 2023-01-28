package com.kopring.realworld.domain.member.db.entity

import com.kopring.realworld.global.audit.AuditBase
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Follow(@ManyToOne val from: Member, @ManyToOne val to: Member) :
    AuditBase() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
