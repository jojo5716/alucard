package com.goodcode.alucard.modelBuilder.model.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/model")
class ModelController {

    @PostMapping("/create")
    fun createModel() : ResponseEntity<Boolean> {
        println("Creating model...")

        return ResponseEntity.status(HttpStatus.OK).body(true)
    }
}