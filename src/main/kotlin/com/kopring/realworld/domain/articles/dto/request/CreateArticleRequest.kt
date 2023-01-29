package com.kopring.realworld.domain.articles.dto.request

import com.kopring.realworld.domain.articles.db.entity.Article
import com.kopring.realworld.domain.articles.db.entity.Tag

class CreateArticleRequest(val article: CreateArticle)

class CreateArticle(val title: String, val description: String, val body: String, val tagList: List<String>?) {
    fun toEntity(article: Article): List<Tag> {
        return tagList!!.map { Tag(it, article) }
    }
}

