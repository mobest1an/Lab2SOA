package com.iver.flatservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class FlatServiceApplication

fun main(args: Array<String>) {
    runApplication<FlatServiceApplication>(*args)
}
