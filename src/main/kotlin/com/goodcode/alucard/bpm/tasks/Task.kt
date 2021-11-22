package com.goodcode.alucard.bpm.tasks

import com.goodcode.alucard.bpm.responses.FetchResponse
import org.springframework.stereotype.Service
import java.util.logging.Logger


@Service
sealed class Task(topic: String) : ITask {
    protected fun complete(task: FetchResponse) {
        Logger.getGlobal().info("Start handling task: $task.")
    }
}