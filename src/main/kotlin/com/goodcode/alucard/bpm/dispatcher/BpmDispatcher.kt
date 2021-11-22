package com.goodcode.alucard.bpm.dispatcher

import com.goodcode.alucard.bpm.requests.TaskRequest
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class BpmDispatcher: IBpmDispatcher {
    override fun dispatch(task: Any) {
        println("DISPATCH: $task")
    }
}