package com.kopring.realworld.domain.articles.db.repository

import com.kopring.realworld.domain.articles.db.entity.Favorite
import org.springframework.data.jpa.repository.JpaRepository

interface FavoriteRepository : JpaRepository<Favorite, Long> {
}
