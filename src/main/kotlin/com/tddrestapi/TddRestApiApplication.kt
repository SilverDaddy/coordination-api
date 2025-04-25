package com.tddrestapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TddRestApiApplication

fun main(args: Array<String>) {
    runApplication<TddRestApiApplication>(*args)
}
