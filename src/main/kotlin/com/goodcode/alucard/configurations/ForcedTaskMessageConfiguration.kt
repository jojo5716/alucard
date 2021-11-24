package com.goodcode.alucard.configurations

import com.goodcode.alucard.bpm.ForcedTaskMessages
import com.goodcode.alucard.gateways.JourneyGateway
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.Executors

@Configuration
class ForcedTaskMessageConfiguration(private val journeyGateway: JourneyGateway) {
    @Bean
    fun forcedTaskMessage(): ForcedTaskMessages {
        val executorService = Executors.newSingleThreadScheduledExecutor()
        return ForcedTaskMessages(
            executorService = executorService,
            journeyGateway = journeyGateway
        )
    }
}
