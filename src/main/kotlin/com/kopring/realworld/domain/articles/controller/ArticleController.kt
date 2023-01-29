package com.kopring.realworld.domain.articles.controller

import com.kopring.realworld.domain.articles.dto.request.CreateArticleRequest
import com.kopring.realworld.domain.articles.dto.response.SingleArticleResponse
import com.kopring.realworld.domain.articles.service.ArticleService
import com.kopring.realworld.global.dto.BaseResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/articles")
class ArticleController(val articleService: ArticleService) {

    @PostMapping
    fun createArticle(@RequestBody createArticleRequest: CreateArticleRequest): BaseResponse<SingleArticleResponse> {
        return BaseResponse(SingleArticleResponse(articleService.createArticle(createArticleRequest.article)), 201)
    }

}
