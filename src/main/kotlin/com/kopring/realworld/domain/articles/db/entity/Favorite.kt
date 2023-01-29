package com.kopring.realworld.domain.articles.db.entity

import com.kopring.realworld.domain.member.db.entity.Member
import com.kopring.realworld.global.audit.AuditBase
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Favorite(@ManyToOne val article: Article, @ManyToOne val favoriteBy: Member) : AuditBase() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
