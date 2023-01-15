package com.kopring.realworld

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class RealworldApplication

fun main(args: Array<String>) {
	runApplication<RealworldApplication>(*args)
}
