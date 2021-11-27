package com.goodcode.alucard.modelBuilder.model.controller

import com.goodcode.alucard.gateways.JourneyGateway
import com.goodcode.alucard.modelBuilder.model.request.RequestSchema
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/model")
class ModelController(
    private val journeyGateway: JourneyGateway,
    @Value("\${bpm.processDefinitionKeys.createModel}") private val createModel: String,
) {

    @PostMapping("/create")
    fun createModel(@RequestBody body: RequestSchema): ResponseEntity<Boolean> {
        journeyGateway.start(createModel, body)

        return ResponseEntity.status(HttpStatus.OK).body(true)
    }
}