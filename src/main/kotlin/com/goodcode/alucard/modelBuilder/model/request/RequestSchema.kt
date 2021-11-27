package com.goodcode.alucard.modelBuilder.model.request

data class RequestSchema(
    val businessKey: String,
    val variables: Map<String, Any>?
)