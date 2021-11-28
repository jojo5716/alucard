package com.goodcode.alucard.bpm.controllers

import com.goodcode.alucard.bpm.dispatcher.ActionDispatcher
import com.goodcode.alucard.bpm.requests.ActionRequest
import com.goodcode.alucard.bpm.requests.BpmInstanceRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/v1/action")
class BpmController(
    private val actionDispatcher: ActionDispatcher
) {
    @PostMapping("")
    fun registerAction(@RequestBody body: ActionRequest): ResponseEntity<Boolean> {
        val bpmInstanceData = BpmInstanceRequest(businessKey=UUID.randomUUID().toString(), variables = body.payload)

        actionDispatcher.dispatch(body.action, bpmInstanceData)

        return ResponseEntity.status(HttpStatus.OK).body(true)
    }
}