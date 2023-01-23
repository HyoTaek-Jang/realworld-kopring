package com.kopring.realworld.auth.dto.response

import com.kopring.realworld.domain.auth.db.entity.Member

class UsersResponse(val email: String, val token: String?, val userName: String, val bio: String?, val image: String?){
    constructor(member: Member) : this(member.email, null, member.userName, null, null)
    constructor(member: Member, token: String) : this(member.email, token, member.userName, null, null)
}

class profile(val userName: String, val bio: String, val image: String, val following: String)
