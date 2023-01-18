package com.kopring.realworld.auth.db.entity

import com.kopring.realworld.global.audit.AuditBase
import javax.persistence.*

@Entity
class Member(@Column(nullable = false, unique = true) var email: String, @Column(nullable = false, unique = true) var password: String, @Column(nullable = false) var userName: String) :
    AuditBase() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
