package com.kopring.realworld.domain.articles.db.repository

import com.kopring.realworld.domain.articles.db.entity.Article
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleRepository : JpaRepository<Article, Long> {
    fun findBySlug(slug: String): Article?
    fun deleteBySlug(slug: String): Int
}
