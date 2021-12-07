package com.goodcode.alucard.bpm.dispatcher

import com.goodcode.alucard.bpm.requests.BpmInstanceRequest
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class ActionDispatcher(
    private val kafkaTemplate: KafkaTemplate<String, Any>
) : IActionDispatcher {
    override fun dispatch(action: String, body: BpmInstanceRequest) {
        when (action) {
            Actions.CREATE_MODEL.actionName -> kafkaTemplate.send(action, body)
            else -> Logger.getGlobal().severe("Action invalid")
        }
    }
}