package com.goodcode.alucard.bpm.tasks

import com.goodcode.alucard.bpm.responses.TaskResponse
import com.goodcode.alucard.bpm.responses.TaskVariablesResponse

sealed interface ITask {
    fun handle(task: TaskResponse): Any
    fun getVariables(taskId: String): TaskVariablesResponse
    fun complete(taskId: String)
}