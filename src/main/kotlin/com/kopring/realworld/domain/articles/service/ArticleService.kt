package com.kopring.realworld.domain.articles.service

import com.kopring.realworld.auth.dto.response.SingleArticleDto
import com.kopring.realworld.domain.articles.NotExistArticleException
import com.kopring.realworld.domain.articles.db.entity.Article
import com.kopring.realworld.domain.articles.db.repository.ArticleRepository
import com.kopring.realworld.domain.articles.db.repository.TagRepository
import com.kopring.realworld.domain.articles.dto.request.CreateArticle
import com.kopring.realworld.domain.articles.dto.request.UpdateArticle
import com.kopring.realworld.global.jwt.SecurityUtils.Companion.getPrincipalMember
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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

    fun updateArticle(slug: String, updateArticleRequest: UpdateArticle): SingleArticleDto {
        val article = articleRepository.findBySlug(slug) ?: throw NotExistArticleException()
        article.update(updateArticleRequest, slug)
        articleRepository.save(article)

        val tagList = tagRepository.findAllByArticle(article)?.map { it.name }

        return SingleArticleDto(article, getPrincipalMember(), tagList)
    }

    @Transactional
    fun deleteArticle(slug: String) {
        articleRepository.deleteBySlug(slug)
    }
}
