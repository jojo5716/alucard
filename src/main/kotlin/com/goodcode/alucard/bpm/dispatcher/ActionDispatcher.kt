package com.goodcode.alucard.bpm.dispatcher

import com.goodcode.alucard.bpm.requests.BpmInstanceRequest
import com.goodcode.alucard.bpm.templates.CreateModelTemplate
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class ActionDispatcher(
    private val createModelTemplate: CreateModelTemplate
): IActionDispatcher {
    override fun dispatch(action: String, body: BpmInstanceRequest) {
        when(action){
            Actions.CREATE_MODEL.actionName -> createModelTemplate.start(body)
            else -> Logger.getGlobal().severe("Action invalid")
        }
    }
}