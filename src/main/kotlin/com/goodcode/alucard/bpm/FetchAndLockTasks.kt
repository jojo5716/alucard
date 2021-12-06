package com.goodcode.alucard.bpm

import com.goodcode.alucard.gateways.JourneyGateway
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class FetchAndLockTasks(private val journeyGateway: JourneyGateway) {

    @KafkaListener(topics = ["\${kafka.topics.fetchTasks}"], groupId = "\${kafka.group-id}")
    fun fetchAndLock() {
        Logger.getGlobal().info("Fetching and locking tasks")
        val topicNames = journeyGateway.fetchTopicNames()
        if (topicNames != null && topicNames.isNotEmpty()) {
            val tasks = journeyGateway.fetchAndLock(topicNames)
        }
    }
}