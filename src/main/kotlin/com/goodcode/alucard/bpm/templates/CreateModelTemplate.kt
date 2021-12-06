package com.goodcode.alucard.bpm.templates

import com.goodcode.alucard.bpm.requests.BpmInstanceRequest
import com.goodcode.alucard.gateways.JourneyGateway
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class CreateModelTemplate(
    journeyGateway: JourneyGateway,
    private val kafkaTemplate: KafkaTemplate<String, BpmInstanceRequest>,
    @Value("\${bpm.processDefinitions.createModel}") private val createModel: String,
    @Value("\${kafka.topics.fetchTasks}") private val fetchTasksTopic: String,
) : BpmActionTemplate(journeyGateway) {

    @KafkaListener(topics = ["\${kafka.topics.startCreateModelInstance}"], groupId = "\${kafka.group-id}")
    override fun start(bpmInstanceRequest: BpmInstanceRequest) {
        startBpmInstance(createModel, bpmInstanceRequest)

        kafkaTemplate.send(fetchTasksTopic, bpmInstanceRequest)
    }
}