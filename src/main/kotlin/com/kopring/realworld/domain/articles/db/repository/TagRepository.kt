package com.kopring.realworld.domain.articles.db.repository

import com.kopring.realworld.domain.articles.db.entity.Tag
import org.springframework.data.jpa.repository.JpaRepository

interface TagRepository : JpaRepository<Tag, Long> {
}
