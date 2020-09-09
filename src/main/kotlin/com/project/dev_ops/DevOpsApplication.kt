package com.project.dev_ops

import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@EnableRabbit
@EnableEurekaServer
@SpringBootApplication
class DevOpsApplication

fun main(args: Array<String>) {
    runApplication<DevOpsApplication>(*args)
}
