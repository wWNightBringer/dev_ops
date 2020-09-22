package com.project.store_shop

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.PropertySource

@PropertySource(value = ["classpath:application-dev.yml"])
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
class StoreShopApplication

fun main(args: Array<String>) {
    runApplication<StoreShopApplication>(*args)
}
