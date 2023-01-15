package com.kopring.realworld.auth.db.entity

import javax.persistence.*

@Entity
class Member(@Column(nullable = false, unique = true) var email: String, @Column(nullable = false, unique = true) var password: String, @Column(nullable = false) var userName: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
