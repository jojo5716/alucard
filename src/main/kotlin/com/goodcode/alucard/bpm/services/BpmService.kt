package com.goodcode.alucard.bpm.services

import com.goodcode.alucard.bpm.templates.TaskTemplate
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class BpmService: IBpmService {
    override fun startInstance(taskTemplate: TaskTemplate) {
        Logger.getGlobal().info("Starting BPM... $taskTemplate")
    }
}