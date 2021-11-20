package com.goodcode.alucard.bpm.tasks

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.logging.Logger

class CreateModelTask(
    val businessKey: String,
    private val variables: Map<String, Any>?,
    @Value("\${kafka.topics.createModelTask}") private val createModelTaskName: String
) : Task(createModelTaskName), ITask {
    override fun handle() {
        Logger.getGlobal().info("Starting CreateModelTask with businessKey: $businessKey")

//        complete()
    }
}
