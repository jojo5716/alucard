package com.goodcode.alucard.configurations

import com.fasterxml.jackson.databind.deser.std.StringDeserializer
import com.goodcode.alucard.bpm.requests.ActionRequest
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer


@EnableKafka
@Configuration
class KafkaConsumerConfiguration {
    @Value(value = "\${kafka.bootstrap-address}")
    private val bootstrapAddress: String? = null

    @Value(value = "\${kafka.group-id}")
    private val groupId: String? = null

    @Value(value = "\${kafka.trusted-packages}")
    private val trustedPackage: String? = null

    @Bean
    fun consumerFactory(): ConsumerFactory<String, ActionRequest> {
        val props: MutableMap<String, Any?> = HashMap()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
        props[ConsumerConfig.GROUP_ID_CONFIG] = groupId
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer::class.java
        props[JsonDeserializer.TRUSTED_PACKAGES] = trustedPackage

        return DefaultKafkaConsumerFactory(props)
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, ActionRequest> {
        val factory: ConcurrentKafkaListenerContainerFactory<String, ActionRequest> =
            ConcurrentKafkaListenerContainerFactory()
        factory.setConsumerFactory(consumerFactory())

        return factory
    }
}