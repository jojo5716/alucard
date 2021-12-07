package com.goodcode.alucard.bpm.tasks

import com.goodcode.alucard.bpm.requests.PayloadSchema
import com.goodcode.alucard.bpm.responses.FetchAndLockResponse
import com.goodcode.alucard.gateways.JourneyGateway
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import java.util.logging.Logger


@Component
class ValidateActionPermission(
    journeyGateway: JourneyGateway,
    @Value("\${kafka.topics.validateActionPermission}") private val validateActionPermission: String,
) : BaseTask(validateActionPermission, journeyGateway), IBaseTask {

    @KafkaListener(topics = ["\${kafka.topics.validateActionPermission}"], groupId = "\${kafka.group-id}")
    override fun execute(fetchAndLockResponse: FetchAndLockResponse) {
        super.execute(fetchAndLockResponse)
        val variables = mapOf("userHasPermissions" to PayloadSchema(value = true, type = "Boolean"))

        try {
            complete(fetchAndLockResponse, variables)
        } catch (ex: Exception) {
            Logger.getGlobal().severe("Error executing task $fetchAndLockResponse: $ex")
        }
    }
}

