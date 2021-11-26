package com.goodcode.alucard.bpm.tasks

import com.goodcode.alucard.bpm.responses.TaskResponse

sealed interface ITask {
    fun handle(task: TaskResponse): Any
}