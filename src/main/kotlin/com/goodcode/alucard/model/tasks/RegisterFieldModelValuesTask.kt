package com.goodcode.alucard.model.tasks

import camundajar.impl.com.google.gson.JsonObject
import camundajar.impl.com.google.gson.JsonParser
import com.goodcode.alucard.bpm.requests.PayloadSchema
import com.goodcode.alucard.bpm.responses.FetchAndLockResponse
import com.goodcode.alucard.bpm.tasks.BaseTask
import com.goodcode.alucard.bpm.tasks.IBaseTask
import com.goodcode.alucard.gateways.JourneyGateway
import com.goodcode.alucard.model.entities.Model
import com.goodcode.alucard.model.fields.Field
import com.goodcode.alucard.model.fields.FieldLoader
import com.goodcode.alucard.model.presenters.DocumentPresenter
import com.goodcode.alucard.model.repositories.DocumentRepository
import com.goodcode.alucard.model.repositories.FieldRepository
import com.goodcode.alucard.model.repositories.ModelRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.util.logging.Logger


@Component
class RegisterFieldModelValuesTask(
    private val modelRepository: ModelRepository,
    private val fieldRepository: FieldRepository,
    private val documentPresenter: DocumentPresenter,
    private val fieldLoader: FieldLoader,
    journeyGateway: JourneyGateway,
    kafkaTemplate: KafkaTemplate<String, Any>,
    @Value("\${kafka.topics.registerFieldModelValues}") private val registerFieldModelValuesTopic: String,
    @Value("\${kafka.topics.fetchTasks}") private val fetchTasksTopic: String
) : BaseTask(registerFieldModelValuesTopic, journeyGateway, kafkaTemplate, fetchTasksTopic), IBaseTask {

    @KafkaListener(topics = ["\${kafka.topics.registerFieldModelValues}"], groupId = "\${kafka.group-id}")
    override fun execute(fetchAndLockResponse: FetchAndLockResponse) {
        super.execute(fetchAndLockResponse)
        try {
            val modelName = fetchAndLockResponse.variables["modelName"]?.value
            val model: Model = modelRepository.findByName(modelName!!)
            val modelFields = fieldRepository.findByModel(model)
            val jsonParser = JsonParser()
            val fieldValuesParsed = jsonParser.parse(fetchAndLockResponse.variables["data"]?.value) as JsonObject
            val documentModel = documentPresenter.create(model)

            modelFields.forEach {
                val field: Field? = fieldLoader.loadFieldByElement(it, fieldValuesParsed.get(it.name))

                if (field !== null) {
                    field.insertData(documentModel)
                } else {
                    Logger.getGlobal().severe("Cannot insert value for field $it")
                }
            }

            complete(
                fetchAndLockResponse, mapOf(
                    "error" to PayloadSchema(value = false, type = "Boolean"),
                    "message" to PayloadSchema(value = "Fields registered successfully", type = "String")
                )
            )
        } catch (ex: Exception) {
            error(
                fetchAndLockResponse, mapOf(
                    "error" to PayloadSchema(value = true, type = "Boolean"),
                    "message" to PayloadSchema(value = ex.message.toString(), type = "String")
                )
            )
        }
    }
}
