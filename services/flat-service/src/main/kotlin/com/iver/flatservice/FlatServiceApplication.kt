package com.iver.flatservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(exclude = [
    UserDetailsServiceAutoConfiguration::class
])
@EntityScan(basePackages = ["com.iver.common.model", "com.iver.flatservice"])
@EnableJpaRepositories(basePackages = ["com.iver.common", "com.iver.flatservice"])
@ComponentScan(basePackages = ["com.iver.common", "com.iver.flatservice"])
class FlatServiceApplication

fun main(args: Array<String>) {
    runApplication<FlatServiceApplication>(*args)
}
