package com.goodcode.alucard.model.tasks

import com.goodcode.alucard.bpm.responses.FetchAndLockResponse
import com.goodcode.alucard.bpm.tasks.BaseTask
import com.goodcode.alucard.bpm.tasks.IBaseTask
import com.goodcode.alucard.model.presenters.ModelPresenter
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription
import org.camunda.bpm.client.task.ExternalTask
import org.camunda.bpm.client.task.ExternalTaskService
import org.camunda.bpm.engine.variable.Variables
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.logging.Logger


@Component
@ExternalTaskSubscription(topicName = "createModelTopic", autoOpen = true)
class CreateModelTask(
    private val modelPresenter: ModelPresenter,
    @Value("\${kafka.topics.createModel}") private val createModel: String
) : BaseTask(createModel), IBaseTask {
    override fun execute(fetchAndLockResponse: FetchAndLockResponse) {
//        Logger.getGlobal().info("Executing external task: $externalTask by external task service: $externalTaskService")
//        try {
//            val modelCreated = modelPresenter.create(externalTask.allVariables)
//            val variables = Variables.createVariables()
//
//            variables["modelId"] = modelCreated.id.toString()
//
//            complete(externalTask, variables, externalTaskService)
//        } catch (ex: Exception) {
//            Logger.getGlobal().severe("Error finishing task $externalTask: $ex")
//        }
    }
}

