package com.goodcode.alucard.bpm

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class FetchAndLockTasks {

    @KafkaListener(topics = ["\${kafka.topics.fetchTasks}"], groupId = "\${kafka.group-id}")
    fun fetchAndLock() {
        println("Fetch and lock...")
    }
}