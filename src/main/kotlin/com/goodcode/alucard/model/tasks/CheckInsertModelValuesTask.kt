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
import com.goodcode.alucard.model.presenters.FieldPresenter
import com.goodcode.alucard.model.repositories.FieldRepository
import com.goodcode.alucard.model.repositories.ModelRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.util.logging.Logger


@Component
class CheckInsertModelValuesTask(
    private val modelRepository: ModelRepository,
    private val fieldPresenter: FieldPresenter,
    private val fieldLoader: FieldLoader,
    journeyGateway: JourneyGateway,
    kafkaTemplate: KafkaTemplate<String, Any>,
    @Value("\${kafka.topics.checkInsertModelValues}") private val checkInsertModelValuesTopic: String,
    @Value("\${kafka.topics.fetchTasks}") private val fetchTasksTopic: String
) : BaseTask(checkInsertModelValuesTopic, journeyGateway, kafkaTemplate, fetchTasksTopic), IBaseTask {

    @KafkaListener(topics = ["\${kafka.topics.checkInsertModelValues}"], groupId = "\${kafka.group-id}")
    override fun execute(fetchAndLockResponse: FetchAndLockResponse) {
        super.execute(fetchAndLockResponse)
        try {
            val modelName = fetchAndLockResponse.variables["modelName"]?.value
            val model: Model = modelRepository.findByName(modelName!!)
            val modelFields = fieldPresenter.findByModel(model)
            val jsonParser = JsonParser()
            val fieldValuesParsed = jsonParser.parse(fetchAndLockResponse.variables["data"]?.value) as JsonObject
            val allFieldsAreOk: Map<String, Boolean> = modelFields.associate {
                val field: Field? = fieldLoader.loadFieldByElement(it, fieldValuesParsed.get(it.name).asString)

                (it.name to (field?.isValidData() ?: false))
            }

            complete(
                fetchAndLockResponse, mapOf(
                    "isValidData" to PayloadSchema(value = false !in allFieldsAreOk.values, type = "Boolean"),
                    "fieldValidations" to PayloadSchema(value = allFieldsAreOk, type = "String"),
                    "message" to PayloadSchema(value = "CheckInsertModelValuesTask message", type = "String")
                )
            )
        } catch (ex: Exception) {
            error(
                fetchAndLockResponse, mapOf(
                    "isValidData" to PayloadSchema(value = false, type = "Boolean"),
                    "fieldValidations" to PayloadSchema(value = "", type = "String"),
                    "message" to PayloadSchema(value = ex.message.toString(), type = "String")
                )
            )
        }
    }
}
