package com.kopring.realworld.auth.dto.response

import com.kopring.realworld.domain.articles.db.entity.Article
import com.kopring.realworld.domain.member.db.entity.Member
import java.time.LocalDateTime

class UsersResponse(val email: String, val token: String?, val userName: String, val bio: String?, val image: String?) {
    constructor(member: Member) : this(member.email, null, member.userName, member.bio, member.image)
    constructor(member: Member, token: String) : this(member.email, token, member.userName, member.bio, member.image)
}

class ProfileResponse(val userName: String, val bio: String?, val image: String?, val following: Boolean) {
    constructor(member: Member, following: Boolean) : this(member.userName, member.bio, member.image, following)
}

class SingleArticle(
    val slug: String?,
    val title: String,
    val description: String?,
    val body: String,
    val tagList: List<String>?,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?,
    val favorited: Boolean,
    val favoritesCount: Long,
    val author: ProfileResponse
) {
    constructor(article: Article, author: Member, tags: List<String>?) : this(
        article.slug,
        article.title,
        article.description,
        article.body,
        tags,
        article.createdDate,
        article.updatedDate,
        false,
        0L,
        ProfileResponse(author, false)
    )
}
