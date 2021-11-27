package com.goodcode.alucard.gateways

import com.goodcode.alucard.bpm.responses.TaskResponse
import com.goodcode.alucard.bpm.responses.TaskVariablesResponse
import com.goodcode.alucard.modelBuilder.model.request.RequestSchema
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
    @Value("\${bpm.endpoints.pendingTaskList}") private val pendingTaskListEndpoint: String,
    @Value("\${bpm.endpoints.taskVariables}") private val taskVariablesEndpoint: String,
    @Value("\${bpm.endpoints.completeTask}") private val completeTaskEndpoint: String
) {
    fun start(processDefinitionKey: String, body: RequestSchema) {
        val request = requestBuilder.post(
            body = body,
            uri = (baseUrl + startProcessEndpoint).replace("\$processDefinitionKey", processDefinitionKey)
        )

        sendRequest<Unit>(request)
    }

    fun getPendingTasks(): Array<TaskResponse> {
        val request = requestBuilder.get(
            uri = (baseUrl + pendingTaskListEndpoint)
        )

        return sendRequest<Array<TaskResponse>>(request).body!!
    }

    fun getTaskVariables(taskId: String): TaskVariablesResponse {
        val request = requestBuilder.get(
            uri = (baseUrl + taskVariablesEndpoint.replace("\$taskId", taskId))
        )

        return sendRequest<TaskVariablesResponse>(request).body!!
    }

    fun complete(taskId: String) {
        val request = requestBuilder.get(
            uri = (baseUrl + completeTaskEndpoint.replace("\$taskId", taskId))
        )

        sendRequest<TaskVariablesResponse>(request).body!!
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