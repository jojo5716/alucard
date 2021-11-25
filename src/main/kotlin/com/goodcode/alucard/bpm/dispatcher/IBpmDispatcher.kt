package com.goodcode.alucard.bpm.dispatcher

import com.goodcode.alucard.bpm.responses.ProcessInstanceResponse

interface IBpmDispatcher {
    fun dispatch(task: ProcessInstanceResponse): Unit
}