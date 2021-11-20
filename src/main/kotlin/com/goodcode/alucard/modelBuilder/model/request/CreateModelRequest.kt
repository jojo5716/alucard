package com.goodcode.alucard.modelBuilder.model.request

data class CreateModelRequest(
    val businessKey: String,
    val variables: Map<String, Any>?
)