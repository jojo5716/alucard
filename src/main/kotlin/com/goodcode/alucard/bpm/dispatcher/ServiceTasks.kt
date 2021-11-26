package com.goodcode.alucard.bpm.dispatcher


interface IServiceTask {
    val activityId: String
    val topic: String
}

enum class ServiceTasks(override val activityId: String, override val topic: String) : IServiceTask {
    CREATE_MODEL(activityId = "CreateModelProcess", topic = "createModel"),
    VALIDATE_ACTION(activityId = "ValidateActionPermissionTask", topic = "validateActionPermission")
}
