package com.goodcode.alucard.configurations

import org.camunda.bpm.client.ExternalTaskClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class CamundaConfiguration(
    @Value("\${bpm.baseUrl}") private val baseUrl: String,
    @Value("\${bpm.workerId}") private val workerId: String,
    @Value("\${bpm.maxTasks}") private val maxTasks: Int,
    @Value("\${bpm.lockDuration}") private val lockDuration: Long,
    ) {
    @Bean
    fun customClient(): ExternalTaskClient {
        return ExternalTaskClient.create()
            .baseUrl(baseUrl)
//            .workerId(workerId)
            .maxTasks(maxTasks)
            .lockDuration(lockDuration)
            .build()
    }
}