package com.project.store_shop.rabbit

import com.project.store_shop.model.vo.ShopVo
import lombok.RequiredArgsConstructor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
class RabbitConsumer {
    val logger: Logger = LoggerFactory.getLogger(RabbitConsumer::class.java)

    @RabbitListener(queues = ["devOps-queue-request"])
    fun getShop(shopVo: ShopVo) {
        logger.info("Rabbit delivered shop id: {}, description: {}", shopVo.id, shopVo.description)
    }
}