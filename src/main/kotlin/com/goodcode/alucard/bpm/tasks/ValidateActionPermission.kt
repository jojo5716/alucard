package com.goodcode.alucard.bpm.tasks

import com.goodcode.alucard.bpm.requests.PayloadSchema
import com.goodcode.alucard.bpm.responses.FetchAndLockResponse
import com.goodcode.alucard.gateways.JourneyGateway
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.util.logging.Logger


@Component
class ValidateActionPermission(
    journeyGateway: JourneyGateway,
    kafkaTemplate: KafkaTemplate<String, Any>,
    @Value("\${kafka.topics.validateActionPermission}") private val validateActionPermission: String,
    @Value("\${kafka.topics.fetchTasks}") private val fetchTasksTopic: String
) : BaseTask(validateActionPermission, journeyGateway, kafkaTemplate, fetchTasksTopic), IBaseTask {

    @KafkaListener(topics = ["\${kafka.topics.validateActionPermission}"], groupId = "\${kafka.group-id}")
    override fun execute(fetchAndLockResponse: FetchAndLockResponse) {
        super.execute(fetchAndLockResponse)

        try {
            complete(
                fetchAndLockResponse, mapOf(
                    "userHasPermissions" to PayloadSchema(value = true, type = "Boolean"),
                    "message" to PayloadSchema(value = "", type = "String")
                )
            )
        } catch (ex: Exception) {
            Logger.getGlobal().severe("Error executing task $fetchAndLockResponse: $ex")

            error(
                fetchAndLockResponse, mapOf(
                    "userHasPermissions" to PayloadSchema(value = false, type = "Boolean"),
                    "message" to PayloadSchema(value = ex.message.toString(), type = "String")
                )
            )
        }
    }
}

