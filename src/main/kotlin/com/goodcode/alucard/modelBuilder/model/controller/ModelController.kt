package com.goodcode.alucard.modelBuilder.model.controller

import com.goodcode.alucard.bpm.services.BpmService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/model")
class ModelController(private val bpmService: BpmService){

    @PostMapping("/create")
    fun createModel() : ResponseEntity<Boolean> {
        println("Creating model...")

        bpmService.startInstance()

        return ResponseEntity.status(HttpStatus.OK).body(true)
    }
}