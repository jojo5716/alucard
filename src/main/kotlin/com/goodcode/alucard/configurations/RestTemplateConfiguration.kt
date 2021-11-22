package com.goodcode.alucard.configurations


import org.apache.http.client.HttpRequestRetryHandler
import org.apache.http.client.config.RequestConfig
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.protocol.HttpContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.ClientHttpRequestFactory
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate
import java.io.IOException
import java.util.logging.Logger

@Configuration
class RestTemplateConfiguration {

    private companion object {
        const val MAX_TIMEOUT = 20000
    }

    @Bean
    fun restTemplate(): RestTemplate? = RestTemplate(clientFactory())

    private fun clientFactory(): ClientHttpRequestFactory {
        val requestConfig = RequestConfig.custom()
            .setConnectTimeout(MAX_TIMEOUT)
            .setSocketTimeout(MAX_TIMEOUT)
            .build()

        val httpClient = HttpClientBuilder
            .create()
            .setDefaultRequestConfig(requestConfig)
            .setRetryHandler(HttpRequestRetry())
            .build()

        return HttpComponentsClientHttpRequestFactory(httpClient)
    }

    class HttpRequestRetry : HttpRequestRetryHandler {

        private companion object {
            const val THREAD_SLEEP = 1000L
            const val MAX_RETRY = 3
        }

        override fun retryRequest(exception: IOException?, executionCount: Int, context: HttpContext?): Boolean {
            Logger.getGlobal().warning("Retry handler, exception: $exception")

            return if (executionCount > MAX_RETRY) {
                Logger.getGlobal().info("Retry handler. Retry number $executionCount. Max $MAX_RETRY")
                false
            } else {
                Logger.getGlobal().info("Retry handler. Thread sleep $THREAD_SLEEP")
                Thread.sleep(THREAD_SLEEP)
                true
            }
        }
    }
}
