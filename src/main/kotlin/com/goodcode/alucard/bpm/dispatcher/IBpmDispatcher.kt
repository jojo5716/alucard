package com.goodcode.alucard.bpm.dispatcher

import com.goodcode.alucard.bpm.responses.TaskResponse

interface IBpmDispatcher {
    fun dispatch(task: TaskResponse): Unit
}