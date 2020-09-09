package com.project.dev_ops

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DevOpsApplication

fun main(args: Array<String>) {
    runApplication<DevOpsApplication>(*args)
}
