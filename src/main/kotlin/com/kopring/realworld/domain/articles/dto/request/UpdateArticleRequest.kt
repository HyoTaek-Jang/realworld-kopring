package com.kopring.realworld.domain.articles.dto.request

class UpdateArticleRequest(val article: UpdateArticle)

class UpdateArticle(val title: String?, val description: String?, val body: String?)

