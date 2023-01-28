package com.kopring.realworld.auth.dto.response

import com.kopring.realworld.domain.member.db.entity.Member

class UsersResponse(val email: String, val token: String?, val userName: String, val bio: String?, val image: String?){
    constructor(member: Member) : this(member.email, null, member.userName, member.bio, member.image)
    constructor(member: Member, token: String) : this(member.email, token, member.userName, member.bio, member.image)
}

class ProfileResponse(val userName: String, val bio: String?, val image: String?, val following: Boolean) {
    constructor(member: Member, following: Boolean) : this(member.userName, member.bio, member.image, following)
}
