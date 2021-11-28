package com.goodcode.alucard.bpm.templates

import com.goodcode.alucard.bpm.requests.BpmInstanceRequest
import com.goodcode.alucard.gateways.JourneyGateway
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class CreateModelTemplate(
    val journeyGateway: JourneyGateway,
    @Value("\${bpm.processDefinitions.createModel}") val createModel: String,
) : BpmActionTemplate() {
    override fun start(variables: BpmInstanceRequest) {
        journeyGateway.start(createModel, variables)
    }
}