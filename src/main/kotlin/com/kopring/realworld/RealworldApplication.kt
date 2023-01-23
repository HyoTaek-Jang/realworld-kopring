package com.kopring.realworld

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class RealworldApplication
fun main(args: Array<String>) {
	runApplication<RealworldApplication>(*args)
}
