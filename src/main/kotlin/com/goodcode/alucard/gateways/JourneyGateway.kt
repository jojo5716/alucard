package com.goodcode.alucard.gateways

import com.goodcode.alucard.bpm.responses.TaskResponse
import com.goodcode.alucard.utils.Variable
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
    @Value("\${bpm.endpoints.startProcess}") private val startProcessEndpoint: String,
    @Value("\${bpm.endpoints.pendingTaskList}") private val pendingTaskList: String
    ) {
    fun start(processDefinitionKey: String, businessKey: String, variables: Map<String, Variable>) {
        val request = requestBuilder.post(
            body = (mapOf("businessKey" to businessKey) + mapOf("variables" to (variables ?: emptyMap()))),
            uri = (baseUrl + startProcessEndpoint).replace("\$processDefinitionKey", processDefinitionKey)
        )

        sendRequest<Unit>(request)
    }

    fun getPendingTasks(): Array<TaskResponse> {
        val request = requestBuilder.get(
            uri = (baseUrl + pendingTaskList)
        )

        return sendRequest<Array<TaskResponse>>(request).body!!
    }

    private inline fun <reified T> sendRequest(request: RequestEntity<Any>): ResponseEntity<T> {
        try {
            Logger.getGlobal().severe("Sending request: $request")
            return restTemplate.exchange(request, T::class.java)
        } catch (ex: Exception) {
            Logger.getGlobal().severe("Request to Journey failed: ${ex.message}")
            throw ex
        }
    }
}