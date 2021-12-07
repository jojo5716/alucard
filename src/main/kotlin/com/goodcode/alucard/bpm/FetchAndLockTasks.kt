package com.goodcode.alucard.bpm

import com.goodcode.alucard.gateways.JourneyGateway
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class FetchAndLockTasks(
    private val journeyGateway: JourneyGateway,
    private val kafkaTemplate: KafkaTemplate<String, Any>
) {

    @KafkaListener(topics = ["\${kafka.topics.fetchTasks}"], groupId = "\${kafka.group-id}")
    fun fetchAndLock() {
        Logger.getGlobal().info("Fetching and locking tasks")
        journeyGateway.fetchAndLock()?.forEach {
            Logger.getGlobal().info("Executing service task $it")

            kafkaTemplate.send(it.topicName, it)
        }

//        val topicNames = journeyGateway.fetchTopicNames()
//        if (topicNames != null && topicNames.isNotEmpty()) {
//            journeyGateway.fetchAndLock(topicNames)?.forEach {
//                Logger.getGlobal().info("Executing service task $it")
//
//                kafkaTemplate.send(it.topicName, it)
//            }
//        }
    }
}