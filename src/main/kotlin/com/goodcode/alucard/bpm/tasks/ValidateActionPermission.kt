package com.goodcode.alucard.bpm.tasks

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.logging.Logger

class ValidateActionPermission(
    val businessKey: String,
    private val variables: Map<String, Any>?,
    @Value("\${kafka.topics.validateActionPermission}") private val validateActionPermissionTaskName: String
) : Task(validateActionPermissionTaskName), ITask {
    override fun handle() {
        Logger.getGlobal().info("Starting ValidateActionPermission with businessKey: $businessKey")

//        complete()
    }
}
