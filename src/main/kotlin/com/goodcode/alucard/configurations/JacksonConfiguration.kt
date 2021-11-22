package com.goodcode.alucard.configurations

import com.fasterxml.jackson.databind.MapperFeature
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import java.text.SimpleDateFormat

@Configuration
class JacksonConfiguration {

    @Bean
    fun jackson2ObjectMapperBuilder() = Jackson2ObjectMapperBuilder()
        .featuresToEnable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
        .dateFormat(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ"))
}
