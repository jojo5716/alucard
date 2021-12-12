package com.goodcode.alucard.bpm.requests

data class ErrorTaskRequest(
    val workerId: String,
    val errorCode: String,
    val errorMessage: String,
    val variables: Map<String, PayloadSchema>
)