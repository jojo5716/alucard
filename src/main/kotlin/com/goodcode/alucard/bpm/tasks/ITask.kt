package com.goodcode.alucard.bpm.tasks

sealed interface ITask {
    fun handle(): Any
}