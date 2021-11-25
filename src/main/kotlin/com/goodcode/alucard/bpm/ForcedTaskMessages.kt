package com.goodcode.alucard.bpm

import com.goodcode.alucard.bpm.dispatcher.BpmDispatcher
import com.goodcode.alucard.gateways.JourneyGateway
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
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
    private val executorService: ScheduledExecutorService,
    private val journeyGateway: JourneyGateway,
    private val bpmDispatcher: BpmDispatcher
) {
    fun consume() {
        try {
            journeyGateway.getProcessInstance().map {
                bpmDispatcher.dispatch(it)
            }
        } catch (ex: Exception) {
            println("ERROR getting process instances :$ex")
        }

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