package com.kopring.realworld.domain.member.exception

import com.kopring.realworld.global.exception.BaseException

class NotMatchPasswordException: BaseException("비밀번호가 일치하지 않습니다.", 400)
