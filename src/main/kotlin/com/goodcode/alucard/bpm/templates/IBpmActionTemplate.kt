package com.goodcode.alucard.bpm.templates

import com.goodcode.alucard.bpm.requests.BpmInstanceRequest


sealed interface IBpmActionTemplate {
    fun start(variables: BpmInstanceRequest)
}