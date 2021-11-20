package com.goodcode.alucard.bpm.services

import com.goodcode.alucard.bpm.templates.TaskTemplate

interface IBpmService {
    fun startInstance(taskTemplate: TaskTemplate): Unit
}