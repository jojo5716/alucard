package com.goodcode.alucard.bpm.responses

data class FetchAndLockVariables(
    val type: String,
    val value: String,
    val valueInfo: Any
)

data class FetchAndLockResponse(
    val activityId: String,
    val activityInstanceId: String,
    val id: String,
    val workerId: String,
    val topicName: String,
    val processInstanceId: String,
    val processDefinitionId: String,
    val processDefinitionKey: String,
    val lockExpirationTime: String,
    val errorMessage: String?,
    val errorDetails: String?,
    val executionId: String?,
    val tenantId: String?,
    val processDefinitionVersionTag: String?,
    val retries: Int?,
    val suspended: Boolean,
    val variables: Map<String, FetchAndLockVariables>
)
