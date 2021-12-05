package com.goodcode.alucard.bpm.templates

import com.goodcode.alucard.bpm.requests.BpmInstanceRequest
import com.goodcode.alucard.gateways.JourneyGateway
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class CreateModelTemplate(
    private val journeyGateway: JourneyGateway,
    @Value("\${bpm.processDefinitions.createModel}") val createModel: String,
) : BpmActionTemplate(journeyGateway) {

    @KafkaListener(topics = ["\${kafka.topics.startCreateModelInstance}"], groupId = "\${kafka.group-id}")
    override fun start(bpmInstanceRequest: BpmInstanceRequest) {
        startBpmInstance(createModel, bpmInstanceRequest)
    }
}