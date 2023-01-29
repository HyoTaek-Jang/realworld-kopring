package com.kopring.realworld.domain.articles.db.entity

import com.kopring.realworld.global.audit.AuditBase
import javax.persistence.*

@Entity
class Tag(val name: String, @ManyToOne val article: Article) : AuditBase() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
