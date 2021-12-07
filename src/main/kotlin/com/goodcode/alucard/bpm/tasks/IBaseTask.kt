package com.goodcode.alucard.bpm.tasks

import com.goodcode.alucard.bpm.requests.PayloadSchema
import com.goodcode.alucard.bpm.responses.FetchAndLockResponse
import org.apache.kafka.clients.admin.NewTopic

interface IBaseTask {
    fun execute(fetchAndLockResponse: FetchAndLockResponse)
    fun complete(
        fetchAndLockResponse: FetchAndLockResponse,
        variables: Map<String, PayloadSchema>?
    )
    fun serviceTaskMessageTopic(): NewTopic
}