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
import java.util.logging.Logger


@Component
class CheckModelExistTask(
    private val modelRepository: ModelRepository,
    journeyGateway: JourneyGateway,
    kafkaTemplate: KafkaTemplate<String, Any>,
    @Value("\${kafka.topics.checkIfModelNameExist}") private val checkIfModelNameExist: String,
    @Value("\${kafka.topics.fetchTasks}") private val fetchTasksTopic: String
) : BaseTask(checkIfModelNameExist, journeyGateway, kafkaTemplate, fetchTasksTopic), IBaseTask {

    @KafkaListener(topics = ["\${kafka.topics.checkIfModelNameExist}"], groupId = "\${kafka.group-id}")
    override fun execute(fetchAndLockResponse: FetchAndLockResponse) {
        super.execute(fetchAndLockResponse)

        try {
            val modelName = fetchAndLockResponse.variables["modelName"]?.value
            val variables = mapOf(
                "modelExist" to PayloadSchema(value = modelRepository.existsByName(modelName!!), type = "Boolean")
            )

            complete(fetchAndLockResponse, variables)
        } catch (ex: Exception) {
            Logger.getGlobal().severe("Error executing task $fetchAndLockResponse: $ex")
        }
    }
}

//@Component
////@ExternalTaskSubscription(topicName = "checkIfModelNameExistTopic", autoOpen = true)
//class CheckModelExistTask(
//    private val modelRepository: ModelRepository,
//    journeyGateway: JourneyGateway,
//    @Value("\${kafka.topics.checkIfModelNameExist}") private val checkIfModelNameExist: String,
//) : BaseTask(checkIfModelNameExist, journeyGateway), IBaseTask {
//
//    @KafkaListener(topics = ["\${kafka.topics.checkIfModelNameExist}"], groupId = "\${kafka.group-id}")
//    override fun execute(fetchAndLockResponse: FetchAndLockResponse) {
//        super.execute(fetchAndLockResponse)
//        val variables = mapOf("modelExist" to PayloadSchema(value = true, type = "Boolean"))
//
//        try {
//            complete(fetchAndLockResponse, variables)
//        } catch (ex: Exception) {
//            Logger.getGlobal().severe("Error executing task $fetchAndLockResponse: $ex")
//        }
//
//        // Logger.getGlobal().info("Executing external task: $externalTask by external task service: $externalTaskService")
////        try {
////            val modelName = externalTask.getVariable<String>("modelName")
////            val variables = Variables.createVariables()
////
////            variables["modelExist"] = modelRepository.existsByName(modelName)
////
////            complete(externalTask, variables, externalTaskService)
////        } catch (ex: Exception){
////            Logger.getGlobal().severe("Error finishing task $externalTask: $ex")
////        }
//    }
//}
//
