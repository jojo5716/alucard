package com.goodcode.alucard.bpm.responses

import com.goodcode.alucard.utils.Variable

data class FetchResponse(
    val id: String,
    val retries: Int?,
    val topicName: String,
    val errorMessage: String?,
    val variables: Map<String, Variable>?,
    val businessKey: String,
    val type: String
)
