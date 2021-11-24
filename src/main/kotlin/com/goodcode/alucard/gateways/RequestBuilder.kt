package com.goodcode.alucard.gateways

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.goodcode.alucard.utils.IGetRequest
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.RequestEntity
import org.springframework.stereotype.Service
import java.net.URI


@Service
class RequestBuilder {
    fun post(body: Any, customHeaders: Map<String, String> = mapOf(), uri: String) = RequestEntity<Any>(
        addFormattedBody(body),
        addHeaders(customHeaders),
        HttpMethod.POST,
        URI(uri)
    )

    fun get(
        params: IGetRequest? = null,
        customHeaders: Map<String, String> = mapOf(),
        uri: String
    ): RequestEntity<Any> = RequestEntity(
        addHeaders(customHeaders),
        HttpMethod.GET,
        URI("$uri${params?.toQuery()?.prependIndent("?").orEmpty()}")
    )

    private fun addFormattedBody(body: Any) = jacksonObjectMapper().writeValueAsString(body)

    private fun addHeaders(customHeaders: Map<String, String>) = HttpHeaders().apply {
        contentType = MediaType.APPLICATION_JSON
        customHeaders.forEach { (key, value) -> add(key, value) }
    }
}
