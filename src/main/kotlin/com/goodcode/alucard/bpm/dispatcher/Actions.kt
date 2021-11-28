package com.goodcode.alucard.bpm.dispatcher

interface IActions {
    val actionName: String
    val processInstanceName: String
}

enum class Actions(override val actionName: String, override val processInstanceName: String) : IActions {
    CREATE_MODEL(actionName = "CREATE_MODEL", processInstanceName = "CreateModelProcess")
}