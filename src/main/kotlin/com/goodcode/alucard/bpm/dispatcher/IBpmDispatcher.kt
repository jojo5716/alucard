package com.goodcode.alucard.bpm.dispatcher

interface IBpmDispatcher {
    fun dispatch(task: Any): Unit
}