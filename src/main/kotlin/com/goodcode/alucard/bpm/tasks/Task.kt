package com.goodcode.alucard.bpm.tasks

import com.goodcode.alucard.bpm.responses.FetchResponse
import java.util.logging.Logger

sealed class Task(topic: String) : ITask {
    init {
        println(topic)
    }

    protected fun complete(task: FetchResponse) {
        Logger.getGlobal().info("Start handling task: $task.")
    }
}