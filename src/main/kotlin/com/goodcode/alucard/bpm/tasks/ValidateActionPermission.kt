package com.goodcode.alucard.bpm.tasks

import com.goodcode.alucard.bpm.responses.FetchAndLockResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component


@Component
class ValidateActionPermission(
    @Value("\${kafka.topics.validateActionPermission}") private val validateActionPermission: String
) : BaseTask(validateActionPermission), IBaseTask {

    @KafkaListener(topics = ["\${kafka.topics.validateActionPermission}"], groupId = "\${kafka.group-id}")
    override fun execute(fetchAndLockResponse: FetchAndLockResponse) {
        println("ValidateActionPermission: $fetchAndLockResponse \n\n\n\n")
//        super.execute(externalTask, externalTaskService)
//        val variables = Variables.createVariables()
//        variables["userHasPermissions"] = true
//
//        try {
//            complete(externalTask, variables, externalTaskService)
//        } catch (ex: Exception) {
//            Logger.getGlobal().severe("Error finishing task: $ex")
//        }
    }
}

