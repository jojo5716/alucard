package com.goodcode.alucard.bpm.tasks

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription
import org.camunda.bpm.client.task.ExternalTask
import org.camunda.bpm.client.task.ExternalTaskService
import org.camunda.bpm.engine.variable.Variables
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.logging.Logger


@Component
@ExternalTaskSubscription(topicName = "validateActionPermissionTopic", autoOpen = true)
class ValidateActionPermission(
    @Value("\${kafka.topics.validateActionPermission}") private val validateActionPermission: String
) : BaseTask(validateActionPermission), IBaseTask {
    override fun execute(externalTask: ExternalTask, externalTaskService: ExternalTaskService) {
        super.execute(externalTask, externalTaskService)
        val variables = Variables.createVariables()
        variables["userHasPermissions"] = true

        try {
            complete(externalTask, variables, externalTaskService)
        } catch (ex: Exception) {
            Logger.getGlobal().severe("Error finishing task: $ex")
        }
    }
}

