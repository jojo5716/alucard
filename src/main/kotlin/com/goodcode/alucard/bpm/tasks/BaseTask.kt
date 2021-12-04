package com.goodcode.alucard.bpm.tasks

import org.camunda.bpm.client.task.ExternalTask
import org.camunda.bpm.client.task.ExternalTaskHandler
import org.camunda.bpm.client.task.ExternalTaskService
import java.util.logging.Logger

abstract class BaseTask : ExternalTaskHandler, IBaseTask {
    override fun execute(externalTask: ExternalTask, externalTaskService: ExternalTaskService) {
        Logger.getGlobal().info("Executing external task: $externalTask by external task service: $externalTaskService")
    }

    override fun complete(
        externalTask: ExternalTask,
        variables: MutableMap<String, Any>,
        externalTaskService: ExternalTaskService
    ) {
        Logger.getGlobal().info("Completing task: $externalTask with variables: $variables")
        if (variables.get("message") == null) {
            variables["message"] = ""
        }
        externalTaskService.complete(externalTask, variables)
    }
}