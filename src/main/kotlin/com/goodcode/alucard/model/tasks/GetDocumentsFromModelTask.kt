package com.goodcode.alucard.model.tasks


import camundajar.impl.com.google.gson.JsonObject
import camundajar.impl.com.google.gson.JsonParser
import com.goodcode.alucard.bpm.requests.PayloadSchema
import com.goodcode.alucard.bpm.responses.FetchAndLockResponse
import com.goodcode.alucard.bpm.tasks.BaseTask
import com.goodcode.alucard.bpm.tasks.IBaseTask
import com.goodcode.alucard.gateways.JourneyGateway
import com.goodcode.alucard.model.entities.Model
import com.goodcode.alucard.model.presenters.DocumentPresenter
import com.goodcode.alucard.model.presenters.FieldPresenter
import com.goodcode.alucard.model.presenters.ModelPresenter
import com.goodcode.alucard.model.services.DocumentService
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.util.logging.Logger


@Component
class GetDocumentsFromModelTask(
    journeyGateway: JourneyGateway,
    kafkaTemplate: KafkaTemplate<String, Any>,
    private val documentService: DocumentService,
    @Value("\${kafka.topics.getDocumentsFromModel}") private val getDocumentsFromModelTopic: String,
    @Value("\${kafka.topics.fetchTasks}") private val fetchTasksTopic: String
) : BaseTask(getDocumentsFromModelTopic, journeyGateway, kafkaTemplate, fetchTasksTopic), IBaseTask {

    @KafkaListener(topics = ["\${kafka.topics.getDocumentsFromModel}"], groupId = "\${kafka.group-id}")
    override fun execute(fetchAndLockResponse: FetchAndLockResponse) {
        super.execute(fetchAndLockResponse)

        try {
            val modelName = fetchAndLockResponse.variables["modelName"]?.value
            val documents = documentService.getDocumentsFromModel(modelName!!)

            complete(
                fetchAndLockResponse, mapOf(
                    "documents" to PayloadSchema(value = "$documents", type = "String"),
                    "message" to PayloadSchema(value = "Document retrieved", type = "String")
                )
            )
        } catch (ex: Exception) {
            error(
                fetchAndLockResponse, mapOf(
                    "error" to PayloadSchema(value = true, type = "Boolean"),
                    "message" to PayloadSchema(value = ex.message.toString(), type = "String"),
                    "documents" to PayloadSchema(value = emptyList<String>(), type = "String")
                )
            )
        }
    }

}
