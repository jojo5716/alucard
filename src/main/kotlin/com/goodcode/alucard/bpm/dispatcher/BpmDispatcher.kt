package com.goodcode.alucard.bpm.dispatcher

import com.goodcode.alucard.bpm.responses.TaskResponse
import com.goodcode.alucard.bpm.tasks.ValidateActionPermission
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class BpmDispatcher(
    private val validateActionPermission: ValidateActionPermission
) : IBpmDispatcher {
    override fun dispatch(task: TaskResponse) {
        Logger.getGlobal().info("Dispatching task: $task")

        when (task.activityId) {
            ServiceTasks.VALIDATE_ACTION.activityId -> validateActionPermission.handle(task)
            else -> Logger.getGlobal().info("Process model not implemented: $task")
        }
    }
}