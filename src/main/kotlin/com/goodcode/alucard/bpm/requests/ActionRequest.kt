package com.goodcode.alucard.bpm.requests

data class PayloadSchema(
    val value: Any,
    val type: String
)

data class ActionRequest(
    val action: String,
    val payload: Map<String, PayloadSchema>
)
