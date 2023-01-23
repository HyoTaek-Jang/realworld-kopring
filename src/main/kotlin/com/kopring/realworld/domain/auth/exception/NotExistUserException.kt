package com.kopring.realworld.domain.auth.exception

import com.kopring.realworld.global.exception.BaseException

class NotExistUserException: BaseException("존재하지 않는 사용자입니다.", 400)
