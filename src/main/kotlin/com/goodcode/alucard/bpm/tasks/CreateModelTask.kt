package com.goodcode.alucard.bpm.tasks

import java.util.logging.Logger

data class CreateModelTask(
    val businessKey: String,
    val variables: Map<String, Any>?
) : Task() {
    override fun handle() {
        Logger.getGlobal().info("CreateModelTemplate.handle with businessKey: $businessKey")
    }

}
