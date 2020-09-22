package com.project.shop_service.rabbit

import com.project.shop_service.model.vo.ShopVo
import lombok.RequiredArgsConstructor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
class RabbitProducer(private val rabbitTemplate: RabbitTemplate) {
    val routingRequest = "devOps-routing-request"
    val exchange = "devOps-exchange"
    val logger: Logger = LoggerFactory.getLogger(RabbitProducer::class.java)

    fun sendDataToStore(shopVo: ShopVo) {
        logger.info("Producer sent {}", shopVo.id)
        rabbitTemplate.convertAndSend(exchange, routingRequest, shopVo)
    }
}