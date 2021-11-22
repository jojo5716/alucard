package com.goodcode.alucard.bpm

import org.springframework.stereotype.Service
import org.springframework.scheduling.annotation.Scheduled
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import java.util.logging.Logger

private const val DELAY_MS: Long = 1000

@Service
class ScheduledForcedTaskMessage(private val forcedTaskMessage: ForcedTaskMessages) {
    @Scheduled(fixedRateString = "\${bpm.pollingDelayMs}")
    fun consume() {
        forcedTaskMessage.consume()
    }
}

class ForcedTaskMessages(
    private val executorService: ScheduledExecutorService
) {
    fun consume() {
        executorService.schedule(
            {
                kotlin.run {
                    Logger.getGlobal().info("Forced task event")
                }
            },
            DELAY_MS,
            TimeUnit.MILLISECONDS
        )
    }
}