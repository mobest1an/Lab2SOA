package com.iver.agencyservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [
    UserDetailsServiceAutoConfiguration::class
])
@EntityScan(basePackages = ["com.iver.common.model", "com.iver.flatservice"])
class AgencyServiceApplication

fun main(args: Array<String>) {
    runApplication<AgencyServiceApplication>(*args)
}
