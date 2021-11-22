package com.goodcode.alucard.configurations

import com.goodcode.alucard.bpm.ForcedTaskMessages
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.Executors

@Configuration
class ForcedTaskMessageConfiguration{
    @Bean
    fun forcedTaskMessage(): ForcedTaskMessages {
        val executorService = Executors.newSingleThreadScheduledExecutor()
        return ForcedTaskMessages(
            executorService = executorService
        )
    }
}
