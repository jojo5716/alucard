package com.goodcode.alucard.bpm.requests

data class TaskRequest(
    val businessKey: String,
    val variables: Map<String, Any>?
)