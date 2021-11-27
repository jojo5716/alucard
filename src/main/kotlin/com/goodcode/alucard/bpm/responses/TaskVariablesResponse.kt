package com.goodcode.alucard.bpm.responses

data class TaskVariablesContentResponse(
    val type: String,
    val value: String?,
    val valueInfo: Any?
)

data class TaskVariablesResponse(
    val modelName: TaskVariablesContentResponse,
    val action: TaskVariablesContentResponse
)