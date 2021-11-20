package com.goodcode.alucard.modelBuilder.model.controller

import com.goodcode.alucard.bpm.services.BpmService
import com.goodcode.alucard.bpm.tasks.CreateModelTask
import com.goodcode.alucard.modelBuilder.model.request.CreateModelRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/model")
class ModelController(private val bpmService: BpmService){

    @PostMapping("/create")
    fun createModel(@RequestBody body: CreateModelRequest) : ResponseEntity<Boolean> {
        val createModelTaskTemplate = CreateModelTask(body.businessKey, body.variables, "createModelTask")

        bpmService.startInstance(createModelTaskTemplate)

        return ResponseEntity.status(HttpStatus.OK).body(true)
    }
}