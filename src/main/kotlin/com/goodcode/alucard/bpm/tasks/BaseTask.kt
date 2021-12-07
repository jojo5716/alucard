package com.goodcode.alucard.bpm.tasks

import com.goodcode.alucard.bpm.requests.PayloadSchema
import com.goodcode.alucard.bpm.responses.FetchAndLockResponse
import com.goodcode.alucard.gateways.JourneyGateway
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import java.util.logging.Logger

abstract class BaseTask(
    private val validateActionPermission: String,
    private val journeyGateway: JourneyGateway
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
        variables: Map<String, PayloadSchema>
    ) {
        Logger.getGlobal().info("Completing task: $fetchAndLockResponse with variables: $variables")
        var messageVariable = mapOf<String, PayloadSchema>()

        if (variables.get("message") == null) {
            messageVariable = mapOf("message" to PayloadSchema(value = "", type = "String"))
        }

        journeyGateway.complete(fetchAndLockResponse.id, variables + messageVariable)
        journeyGateway.fetchAndLock()

    }
}