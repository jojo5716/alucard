package com.goodcode.alucard.gateways

import com.goodcode.alucard.bpm.requests.BpmInstanceRequest
import com.goodcode.alucard.bpm.requests.FetchAndLockRequest
import com.goodcode.alucard.bpm.requests.Topic
import com.goodcode.alucard.bpm.responses.FetchAndLockResponse
import org.camunda.bpm.client.task.ExternalTask
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.logging.Logger

@Service
class JourneyGateway(
    private val requestBuilder: RequestBuilder,
    private val restTemplate: RestTemplate,
    @Value("\${bpm.baseUrl}") private val baseUrl: String,
    @Value("\${bpm.workerId}") private val workerId: String,
    @Value("\${bpm.maxTasks}") private val maxTasks: Int,
    @Value("\${bpm.usePriority}") private val usePriority: Boolean,
    @Value("\${bpm.lockDuration}") private val lockDuration: Long,
    @Value("\${bpm.endpoints.startProcess}") private val startProcessEndpoint: String,
    @Value("\${bpm.endpoints.fetchAndLock}") private val fetchAndLockEndpoint: String,
    @Value("\${bpm.endpoints.fetchTopicNames}") private val fetchTopicNamesEndpoint: String
) {
    fun start(processDefinitionKey: String, body: BpmInstanceRequest) {
        val request = requestBuilder.post(
            body = body,
            uri = (baseUrl + startProcessEndpoint).replace("\$processDefinitionKey", processDefinitionKey)
        )

        sendRequest<Unit>(request)
    }

    fun fetchAndLock(topicNames: Array<String>): Array<FetchAndLockResponse>? {
        val topicNamesList = topicNames.map { Topic(
            topicName = it,
            lockDuration = lockDuration
        ) }

        val request = requestBuilder.post(
            body = FetchAndLockRequest(
                workerId = workerId,
                maxTasks = maxTasks,
                usePriority = usePriority,
                topics = topicNamesList
            ),
            uri = (baseUrl + fetchAndLockEndpoint)
        )

        return sendRequest<Array<FetchAndLockResponse>>(request).body
    }

    fun fetchTopicNames(): Array<String>? {
        val request = requestBuilder.get(
            uri = (baseUrl + fetchTopicNamesEndpoint)
        )

        return sendRequest<Array<String>>(request).body
    }

    private inline fun <reified T> sendRequest(request: RequestEntity<Any>): ResponseEntity<T> {
        try {
            Logger.getGlobal().info("Sending request: $request")
            return restTemplate.exchange(request, T::class.java)
        } catch (ex: Exception) {
            Logger.getGlobal().severe("Request to Journey failed: ${ex.message}")
            throw ex
        }
    }
}