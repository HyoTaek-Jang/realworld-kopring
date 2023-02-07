package com.kopring.realworld.domain.articles.controller

import com.kopring.realworld.domain.articles.dto.request.CreateArticleRequest
import com.kopring.realworld.domain.articles.dto.request.UpdateArticleRequest
import com.kopring.realworld.domain.articles.dto.response.SingleArticleResponse
import com.kopring.realworld.domain.articles.service.ArticleService
import com.kopring.realworld.global.dto.BaseResponse
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
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

    @PutMapping("/{slug}")
    fun updateArticle(@PathVariable slug: String, @RequestBody updateArticleRequest: UpdateArticleRequest): BaseResponse<SingleArticleResponse> {
        return BaseResponse(SingleArticleResponse(articleService.updateArticle(slug, updateArticleRequest.article)), 201)
    }

    @DeleteMapping("/{slug}")
    fun deleteArticle(@PathVariable slug: String): BaseResponse<String> {
        articleService.deleteArticle(slug)
        return BaseResponse("Article 삭제를 완료했습니다.", 201)
    }

    @GetMapping("/{slug}")
    fun getArticle(@PathVariable slug: String): BaseResponse<SingleArticleResponse> {
        return BaseResponse(SingleArticleResponse(articleService.getArticle(slug)), 200)
    }

}
