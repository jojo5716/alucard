package com.goodcode.alucard.bpm.services

import com.goodcode.alucard.bpm.tasks.Task

interface IBpmService {
    fun startInstance(taskTemplate: Task): Unit
}