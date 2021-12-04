package com.goodcode.alucard.configurations

import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaAdmin

@Configuration
class KafkaTopicConfiguration {
    @Value(value = "\${kafka.bootstrap-address}")
    private val bootstrapAddress: String? = null

    @Bean
    fun kafkaAdmin(): KafkaAdmin {
        val configs: MutableMap<String, Any?> = HashMap()
        configs[AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress

        return KafkaAdmin(configs)
    }

    @Bean
    fun serviceTaskMessageTopic(): NewTopic {
        return NewTopic("service-task-message-topic", 1, 1.toShort())
    }
}