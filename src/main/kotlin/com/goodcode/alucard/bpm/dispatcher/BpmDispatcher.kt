package com.goodcode.alucard.bpm.dispatcher

import com.goodcode.alucard.bpm.responses.ProcessInstanceResponse
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class BpmDispatcher: IBpmDispatcher {
    override fun dispatch(task: ProcessInstanceResponse) {
        Logger.getGlobal().info("Dispatching task: $task")

    }
}