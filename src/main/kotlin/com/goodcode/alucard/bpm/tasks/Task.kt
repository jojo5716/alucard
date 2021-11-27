package com.goodcode.alucard.bpm.tasks

import com.goodcode.alucard.bpm.responses.FetchResponse
import com.goodcode.alucard.bpm.responses.TaskVariablesResponse
import com.goodcode.alucard.gateways.JourneyGateway
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
sealed class Task(
    private val journeyGateway: JourneyGateway
): ITask {
    protected fun complete(task: FetchResponse) {
        Logger.getGlobal().info("Start handling task: $task.")
    }

    override fun getVariables(taskId: String) : TaskVariablesResponse = journeyGateway.getTaskVariables(taskId)

    override fun complete(taskId: String)  = journeyGateway.complete(taskId)
}