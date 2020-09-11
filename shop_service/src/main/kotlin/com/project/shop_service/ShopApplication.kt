package com.project.shop_service

import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableRabbit
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
class ShopApplication

fun main(args: Array<String>) {
    runApplication<ShopApplication>(*args)
}
