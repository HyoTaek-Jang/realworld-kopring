package com.kopring.realworld.domain.articles.db.entity

import com.kopring.realworld.domain.articles.dto.request.UpdateArticle
import com.kopring.realworld.domain.member.db.entity.Member
import com.kopring.realworld.global.audit.AuditBase
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
class Article(
    @Column(nullable = false, unique = true) var slug: String,
    @Column(nullable = false) var title: String,
    var description: String?,
    @Column(nullable = false, columnDefinition = "TEXT") var body: String,
    @ManyToOne val author: Member,
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "article") val favoriteList: MutableList<Favorite> = ArrayList(),
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "article") val tagList: MutableList<Tag> = ArrayList()
) : AuditBase() {
    fun update(updateArticle: UpdateArticle, slug: String) {
        title = updateArticle.title ?: title
        description = updateArticle.description ?: description
        body = updateArticle.body ?: body
        this.slug = slug
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
