package com.goodcode.alucard.bpm.responses

data class ProcessInstanceResponse(
    val links: List<Any>,
    val id: String,
    val definitionId: String,
    val businessKey: String,
    val caseInstanceId: String?,
    val ended: Boolean,
    val suspended: Boolean,
    val tenantId: String,
)