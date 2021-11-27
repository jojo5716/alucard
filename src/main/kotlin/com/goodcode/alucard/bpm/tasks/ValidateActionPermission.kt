package com.goodcode.alucard.bpm.tasks

import com.goodcode.alucard.bpm.responses.TaskResponse
import com.goodcode.alucard.gateways.JourneyGateway
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class ValidateActionPermission(journeyGateway: JourneyGateway) : Task(journeyGateway), ITask {
    override fun handle(task: TaskResponse) {
        Logger.getGlobal().info("Starting ValidateActionPermission with id ${task.id}")
        val variables = getVariables(task.parentActivityInstanceId)
        println(variables)
    }
}
