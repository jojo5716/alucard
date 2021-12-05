package com.goodcode.alucard.bpm.templates

import com.goodcode.alucard.bpm.requests.BpmInstanceRequest
import com.goodcode.alucard.gateways.JourneyGateway
import java.util.logging.Logger

sealed class BpmActionTemplate(
    private val journeyGateway: JourneyGateway,
): IBpmActionTemplate {
    fun startBpmInstance(instanceName: String, variables: BpmInstanceRequest) {
        Logger.getGlobal().info("Starting new BPM instance: $instanceName")
        journeyGateway.start(instanceName, variables)
    }
}
