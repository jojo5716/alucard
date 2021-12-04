package com.goodcode.alucard.bpm.tasks

import org.camunda.bpm.client.task.ExternalTask
import org.camunda.bpm.client.task.ExternalTaskService

interface IBaseTask {
    fun execute(externalTask: ExternalTask, externalTaskService: ExternalTaskService)
    fun complete(
        externalTask: ExternalTask,
        variables: MutableMap<String, Any>,
        externalTaskService: ExternalTaskService
    )
}