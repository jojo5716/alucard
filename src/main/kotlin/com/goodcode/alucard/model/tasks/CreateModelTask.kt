package com.goodcode.alucard.model.tasks


import camundajar.impl.com.google.gson.JsonObject
import camundajar.impl.com.google.gson.JsonParser
import com.goodcode.alucard.bpm.requests.PayloadSchema
import com.goodcode.alucard.bpm.responses.FetchAndLockResponse
import com.goodcode.alucard.bpm.tasks.BaseTask
import com.goodcode.alucard.bpm.tasks.IBaseTask
import com.goodcode.alucard.gateways.JourneyGateway
import com.goodcode.alucard.model.presenters.FieldPresenter
import com.goodcode.alucard.model.presenters.ModelPresenter
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.util.logging.Logger


@Component
class CreateModelTask(
    journeyGateway: JourneyGateway,
    kafkaTemplate: KafkaTemplate<String, Any>,
    private val modelPresenter: ModelPresenter,
    private val fieldPresenter: FieldPresenter,
    @Value("\${kafka.topics.createModel}") private val createModelTopic: String,
    @Value("\${kafka.topics.fetchTasks}") private val fetchTasksTopic: String
) : BaseTask(createModelTopic, journeyGateway, kafkaTemplate, fetchTasksTopic), IBaseTask {

    @KafkaListener(topics = ["\${kafka.topics.createModel}"], groupId = "\${kafka.group-id}")
    override fun execute(fetchAndLockResponse: FetchAndLockResponse) {
        super.execute(fetchAndLockResponse)

        try {
            val jsonParser = JsonParser()
            val fieldsParsed = jsonParser.parse(fetchAndLockResponse.variables.get("fields")?.value)
            val modelCreated = modelPresenter.create(fetchAndLockResponse.variables)

            (fieldsParsed as Iterable<*>).forEach {
                val field: JsonObject = it as JsonObject
                fieldPresenter.create(field, modelCreated)
            }

            val variables = mapOf(
                "modelId" to PayloadSchema(value = modelCreated.id.toString(), type = "String")
            )

            complete(fetchAndLockResponse, variables)
        } catch (ex: Exception) {
            Logger.getGlobal().severe("Error executing task $fetchAndLockResponse: $ex")

            error(fetchAndLockResponse, mapOf("ex" to PayloadSchema(type = "String", value = ex.message.toString())))
        }
    }

}
