package com.goodcode.alucard.bpm.dispatcher

import com.goodcode.alucard.bpm.requests.BpmInstanceRequest

interface IActionDispatcher {
    fun dispatch(action: String, body: BpmInstanceRequest)
}