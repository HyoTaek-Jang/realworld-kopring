package com.kopring.realworld.domain.articles

import com.kopring.realworld.global.exception.BaseException

class NotExistArticleException : BaseException("아티클이 존재하지 않습니다.", 400) {
}
