package com.iver.agencyservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication(exclude = [
    UserDetailsServiceAutoConfiguration::class
])
@EntityScan(basePackages = ["com.iver.common.model", "com.iver.agencyservice"])
@ComponentScan(basePackages = ["com.iver.common", "com.iver.agencyservice"])
class AgencyServiceApplication

fun main(args: Array<String>) {
    runApplication<AgencyServiceApplication>(*args)
}
