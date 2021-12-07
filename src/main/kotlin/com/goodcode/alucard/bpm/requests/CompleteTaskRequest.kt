package com.goodcode.alucard.bpm.requests

data class CompleteTaskRequest(
    val workerId: String,
    val variables: Map<String, PayloadSchema>
)