package com.kopring.realworld.domain.articles.db.entity

import com.kopring.realworld.domain.member.db.entity.Member
import com.kopring.realworld.global.audit.AuditBase
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Article(
    val slug: String?,
    @Column(nullable = false) val title: String,
    val description: String?,
    @Column(nullable = false, columnDefinition = "TEXT") val body: String,
    @ManyToOne val author: Member
) : AuditBase() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
