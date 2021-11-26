package com.goodcode.alucard.bpm.tasks

import com.goodcode.alucard.bpm.responses.TaskResponse
import com.goodcode.alucard.gateways.JourneyGateway
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class CreateModelTask(journeyGateway: JourneyGateway): Task(journeyGateway), ITask {
    override fun handle(task: TaskResponse) {
        Logger.getGlobal().info("Starting CreateModelTask with businessKey: $task")
    }
}
