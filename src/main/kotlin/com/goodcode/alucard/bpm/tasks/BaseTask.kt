package com.goodcode.alucard.bpm.tasks

import org.apache.kafka.clients.admin.NewTopic
import org.camunda.bpm.client.task.ExternalTask
import org.camunda.bpm.client.task.ExternalTaskHandler
import org.camunda.bpm.client.task.ExternalTaskService
import org.springframework.context.annotation.Bean
import java.util.logging.Logger

abstract class BaseTask(private val validateActionPermission: String) : ExternalTaskHandler, IBaseTask {
    @Bean
    fun serviceTaskMessageTopic(): NewTopic {
        return NewTopic(validateActionPermission, 1, 1.toShort())
    }
    
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