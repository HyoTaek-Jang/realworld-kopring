package com.kopring.realworld.domain.member.exception

import com.kopring.realworld.global.exception.BaseException

class ExistUserException: BaseException("이미 존재하는 이메일입니다.", 400)
