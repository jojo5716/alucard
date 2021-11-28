package com.goodcode.alucard.bpm.tasks

import org.camunda.bpm.client.impl.EngineClientException
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription
import org.camunda.bpm.client.task.ExternalTask
import org.camunda.bpm.client.task.ExternalTaskHandler
import org.camunda.bpm.client.task.ExternalTaskService
import org.camunda.bpm.engine.variable.Variables
import org.springframework.stereotype.Component
import java.util.logging.Logger


@Component
@ExternalTaskSubscription(topicName = "validateActionPermissionTopic", autoOpen = true)
class ValidateActionPermission : ExternalTaskHandler {
    override fun execute(externalTask: ExternalTask, externalTaskService: ExternalTaskService) {
        Logger.getGlobal().info("Executing external task: $externalTask by external task service: $externalTaskService")
        val variables = Variables.createVariables()
        variables["userHasPermissions"] = false

        try {
            externalTaskService.complete(externalTask, variables)
        } catch (ex: Exception){
            Logger.getGlobal().severe("Error finishing task: $ex")

        }
    }
}

