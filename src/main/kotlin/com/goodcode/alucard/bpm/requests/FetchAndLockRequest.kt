package com.goodcode.alucard.bpm.requests

data class Topic(
    val topicName: String,
    val lockDuration: Long
)

data class FetchAndLockRequest(
    val workerId: String,
    val maxTasks: Int,
    val usePriority: Boolean,
    val topics: List<Topic>?
)
