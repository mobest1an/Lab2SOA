package com.iver.agencyservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class AgencyServiceApplication

fun main(args: Array<String>) {
    runApplication<AgencyServiceApplication>(*args)
}
