package com.goodcode.alucard.model.workflows

import com.goodcode.alucard.model.repositories.ModelRepository
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription
import org.camunda.bpm.client.task.ExternalTask
import org.camunda.bpm.client.task.ExternalTaskHandler
import org.camunda.bpm.client.task.ExternalTaskService
import org.camunda.bpm.engine.variable.Variables
import org.springframework.stereotype.Component
import java.util.logging.Logger


@Component
@ExternalTaskSubscription(topicName = "checkIfModelNameExistTopic", autoOpen = true)
class CheckModelExistTask(
    private val modelRepository: ModelRepository
) : ExternalTaskHandler {
    override fun execute(externalTask: ExternalTask, externalTaskService: ExternalTaskService) {
        Logger.getGlobal().info("Executing external task: $externalTask by external task service: $externalTaskService")
        try {
            val modelName = externalTask.getVariable<String>("modelName")
            val variables = Variables.createVariables()

            variables["message"] = ""
            variables["modelExist"] = modelRepository.existsByName(modelName)

            externalTaskService.complete(externalTask, variables)
        } catch (ex: Exception){
            Logger.getGlobal().severe("Error finishing task $externalTask: $ex")
        }
    }
}

