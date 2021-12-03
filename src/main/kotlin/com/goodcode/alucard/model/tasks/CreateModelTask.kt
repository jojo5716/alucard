package com.goodcode.alucard.model.tasks

import com.goodcode.alucard.model.presenters.ModelPresenter
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription
import org.camunda.bpm.client.task.ExternalTask
import org.camunda.bpm.client.task.ExternalTaskHandler
import org.camunda.bpm.client.task.ExternalTaskService
import org.camunda.bpm.engine.variable.Variables
import org.springframework.stereotype.Component
import java.util.logging.Logger


@Component
@ExternalTaskSubscription(topicName = "createModelTopic", autoOpen = true)
class CreateModelTask(
    private val modelPresenter: ModelPresenter
) : ExternalTaskHandler {
    override fun execute(externalTask: ExternalTask, externalTaskService: ExternalTaskService) {
        Logger.getGlobal().info("Executing external task: $externalTask by external task service: $externalTaskService")
        try {
            val modelCreated = modelPresenter.create(externalTask.allVariables)
            val variables = Variables.createVariables()

            variables["modelId"] = modelCreated.id.toString()

            externalTaskService.complete(externalTask, variables)
        } catch (ex: Exception) {
            Logger.getGlobal().severe("Error finishing task $externalTask: $ex")
        }
    }
}

