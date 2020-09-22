package com.project.shop_service.config

import lombok.RequiredArgsConstructor
import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory


@EnableRabbit
@RequiredArgsConstructor
@Configuration
class RabbitMqConfig {
    companion object {
        const val routing_request = "devOps-routing-request"

        const val routing_response = "devOps-routing-response"

        const val exchange = "devOps-exchange"

        const val queue_name_request = "devOps-queue-request"

        const val queue_name_response = "devOps-queue-response"

        const val dead_letter_routing = "devOps-dead-letter"

        const val dead_letter_exchange = "devOps-dead-letter-exchange"

        const val dead_letter_queue_name = "devOps-dead-letter-queue"
    }

    @Bean
    fun deadLetterExchange(): DirectExchange? {
        return DirectExchange(dead_letter_exchange)
    }

    @Bean
    fun exchange(): DirectExchange? {
        return DirectExchange(exchange)
    }

    @Bean
    fun deadLetterQueue(): Queue? {
        return QueueBuilder.durable(dead_letter_queue_name).build()
    }

    @Bean
    fun queueRequest(): Queue? {
        return QueueBuilder.durable(queue_name_request)
                .withArgument("x-dead-letter-exchange", dead_letter_exchange)
                .withArgument("x-dead-letter-routing-key", dead_letter_routing).build();
    }

    @Bean
    fun queueResponse(): Queue? {
        return QueueBuilder.durable(queue_name_response)
                .withArgument("x-dead-letter-exchange", dead_letter_exchange)
                .withArgument("x-dead-letter-routing-key", dead_letter_routing).build()
    }

    @Bean
    fun bindingRequest(): Binding? {
        return BindingBuilder.bind(queueRequest()).to(exchange()).with(routing_request)
    }

    @Bean
    fun bindingResponse(): Binding? {
        return BindingBuilder.bind(queueResponse()).to(exchange()).with(routing_response)
    }

    @Bean
    fun dlqBinding(): Binding? {
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with(dead_letter_routing)
    }

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate? {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = producerJackson2MessageConverter()
        return rabbitTemplate
    }

    @Bean
    fun producerJackson2MessageConverter(): Jackson2JsonMessageConverter  {
        return Jackson2JsonMessageConverter()
    }

}