package com.goodcode.alucard.bpm.dispatcher

interface IActions {
    val actionName: String
}

enum class Actions(override val actionName: String) : IActions {
    CREATE_MODEL(actionName = "CREATE_MODEL"),
    INSERT_MODEL(actionName = "INSERT_MODEL"),
    GET_DOCUMENTS_MODEL(actionName = "GET_DOCUMENTS_MODEL")
}