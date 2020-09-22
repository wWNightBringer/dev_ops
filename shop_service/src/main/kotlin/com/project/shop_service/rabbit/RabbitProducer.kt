package com.project.shop_service.rabbit

import com.fasterxml.jackson.databind.ObjectMapper
import com.project.shop_service.config.RabbitMqConfig
import com.project.shop_service.model.vo.ShopVo
import lombok.RequiredArgsConstructor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
class RabbitProducer(private val rabbitTemplate: RabbitTemplate) {
    val logger: Logger = LoggerFactory.getLogger(RabbitProducer::class.java)

    fun sendDataToStore(shopVo: ShopVo) {
        logger.info("Producer sent {}", shopVo.id)
        val objectMapper = ObjectMapper()
        val msg = objectMapper.writeValueAsString(shopVo)
        rabbitTemplate.convertAndSend(RabbitMqConfig.exchange, RabbitMqConfig.routing_request, shopVo)
    }
}