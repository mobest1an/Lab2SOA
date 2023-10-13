package com.iver.flatservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [
    UserDetailsServiceAutoConfiguration::class
])
@EntityScan(basePackages = ["com.iver.common.model", "com.iver.flatservice"])
class FlatServiceApplication

fun main(args: Array<String>) {
    runApplication<FlatServiceApplication>(*args)
}
