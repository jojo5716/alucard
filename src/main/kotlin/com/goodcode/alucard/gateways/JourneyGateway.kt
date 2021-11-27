package com.goodcode.alucard.gateways

import com.goodcode.alucard.modelBuilder.model.request.RequestCreateModelSchema
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
    @Value("\${bpm.endpoints.startProcess}") private val startProcessEndpoint: String
) {
    fun start(processDefinitionKey: String, body: RequestCreateModelSchema) {
        val request = requestBuilder.post(
            body = body,
            uri = (baseUrl + startProcessEndpoint).replace("\$processDefinitionKey", processDefinitionKey)
        )

        sendRequest<Unit>(request)
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