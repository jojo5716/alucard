package com.goodcode.alucard.bpm.templates

import com.goodcode.alucard.bpm.requests.BpmInstanceRequest
import com.goodcode.alucard.gateways.JourneyGateway
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class GetDocumentsFromModelTemplate(
    journeyGateway: JourneyGateway,
    private val kafkaTemplate: KafkaTemplate<String, Any>,
    @Value("\${bpm.processDefinitions.getDocumentsFromModel}") private val getDocumentsFromModel: String,
    @Value("\${kafka.topics.fetchTasks}") private val fetchTasksTopic: String,
) : BpmActionTemplate(journeyGateway) {

    @KafkaListener(topics = ["\${kafka.topics.startGetDocumentsFromModelInstance}"], groupId = "\${kafka.group-id}")
    override fun start(bpmInstanceRequest: BpmInstanceRequest) {
        startBpmInstance(getDocumentsFromModel, bpmInstanceRequest)

        kafkaTemplate.send(fetchTasksTopic, bpmInstanceRequest)
    }
}