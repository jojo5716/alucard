package com.goodcode.alucard.bpm.tasks

import com.goodcode.alucard.bpm.responses.TaskResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class ValidateActionPermission: Task("validateActionPermissionTaskName"), ITask {
    override fun handle(task: TaskResponse) {
        Logger.getGlobal().info("Starting ValidateActionPermission with businessKey")

    }
}
