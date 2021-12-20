package com.goodcode.alucard.model.tasks

import com.goodcode.alucard.bpm.requests.PayloadSchema
import com.goodcode.alucard.bpm.responses.FetchAndLockResponse
import com.goodcode.alucard.bpm.tasks.BaseTask
import com.goodcode.alucard.bpm.tasks.IBaseTask
import com.goodcode.alucard.gateways.JourneyGateway
import com.goodcode.alucard.model.repositories.ModelRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component


@Component
class CheckModelExistTask(
    journeyGateway: JourneyGateway,
    kafkaTemplate: KafkaTemplate<String, Any>,
    private val modelRepository: ModelRepository,
    @Value("\${kafka.topics.checkIfModelNameExist}") private val checkIfModelNameExist: String,
    @Value("\${kafka.topics.fetchTasks}") private val fetchTasksTopic: String
) : BaseTask(checkIfModelNameExist, journeyGateway, kafkaTemplate, fetchTasksTopic), IBaseTask {

    @KafkaListener(topics = ["\${kafka.topics.checkIfModelNameExist}"], groupId = "\${kafka.group-id}")
    override fun execute(fetchAndLockResponse: FetchAndLockResponse) {
        super.execute(fetchAndLockResponse)

        try {
            val modelName = fetchAndLockResponse.variables["modelName"]?.value
            val modelExist = modelRepository.existsByName(modelName!!)
            val message: String = if (!modelExist) "Model does not exist" else ""

            complete(
                fetchAndLockResponse, mapOf(
                    "modelExist" to PayloadSchema(value = modelExist, type = "Boolean"),
                    "message" to PayloadSchema(value = message, type = "String")
                )
            )
        } catch (ex: Exception) {
            error(
                fetchAndLockResponse, mapOf(
                    "modelExist" to PayloadSchema(value = true, type = "Boolean"),
                    "error" to PayloadSchema(value = true, type = "Boolean"),
                    "message" to PayloadSchema(value = ex.message.toString(), type = "String")
                )
            )
        }
    }
}
