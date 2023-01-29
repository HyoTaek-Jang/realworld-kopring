package com.kopring.realworld.domain.articles.service

import com.kopring.realworld.auth.dto.response.SingleArticleDto
import com.kopring.realworld.domain.articles.db.entity.Article
import com.kopring.realworld.domain.articles.db.repository.ArticleRepository
import com.kopring.realworld.domain.articles.db.repository.TagRepository
import com.kopring.realworld.domain.articles.dto.request.CreateArticle
import com.kopring.realworld.global.jwt.SecurityUtils.Companion.getPrincipalMember
import org.springframework.stereotype.Service

@Service
class ArticleService(val articleRepository: ArticleRepository, val tagRepository: TagRepository) {
    fun createArticle(createArticleRequest: CreateArticle): SingleArticleDto {
        val article = Article(createArticleRequest.title, createArticleRequest.title, createArticleRequest.description, createArticleRequest.body, getPrincipalMember())
        articleRepository.save(article)
        if (createArticleRequest.tagList != null) {
            tagRepository.saveAll(createArticleRequest.toEntity(article))
        }
        return SingleArticleDto(article, getPrincipalMember(), createArticleRequest.tagList)
    }
}
