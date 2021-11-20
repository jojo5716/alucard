package com.goodcode.alucard.bpm.services

import com.goodcode.alucard.bpm.tasks.CreateModelTask
import com.goodcode.alucard.bpm.tasks.Task
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class BpmService: IBpmService {
    override fun startInstance(taskTemplate: Task) {
        Logger.getGlobal().info("Starting BPM... $taskTemplate")

        when(taskTemplate) {
            is CreateModelTask -> taskTemplate.handle()
        }
    }
}