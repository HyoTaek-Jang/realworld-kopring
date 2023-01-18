package com.kopring.realworld.global.audit

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AuditBase {
    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdDate: LocalDateTime? = null

    @LastModifiedDate
    @Column(nullable = false)
    var updatedDate: LocalDateTime? = null
}
