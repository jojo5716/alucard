package com.goodcode.alucard.bpm.tasks

import com.goodcode.alucard.bpm.requests.PayloadSchema
import com.goodcode.alucard.bpm.responses.FetchAndLockResponse
import com.goodcode.alucard.gateways.JourneyGateway
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.kafka.core.KafkaTemplate
import java.util.logging.Logger


abstract class BaseTask(
    private val validateActionPermission: String,
    private val journeyGateway: JourneyGateway,
    private val kafkaTemplate: KafkaTemplate<String, Any>,
    @Value("\${kafka.topics.fetchTasks}") private val fetchTasksTopic: String
) : IBaseTask {

    @Bean
    override fun serviceTaskMessageTopic(): NewTopic {
        return NewTopic(validateActionPermission, 1, 1.toShort())
    }

    override fun execute(fetchAndLockResponse: FetchAndLockResponse) {
        Logger.getGlobal().info("Executing external task: $fetchAndLockResponse")
    }

    override fun complete(
        fetchAndLockResponse: FetchAndLockResponse,
        variables: Map<String, PayloadSchema>?
    ) {
        Logger.getGlobal().info("Completing task: $fetchAndLockResponse with variables: $variables")

        journeyGateway.complete(fetchAndLockResponse.id, variables)
        kafkaTemplate.send(fetchTasksTopic, null)
    }

    override fun error(fetchAndLockResponse: FetchAndLockResponse, variables: Map<String, PayloadSchema>?) {
        Logger.getGlobal().severe("Error executing task: $fetchAndLockResponse with variables: $variables")

       complete(fetchAndLockResponse, variables)
    }
}