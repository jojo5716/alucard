package com.goodcode.alucard.model.workflows

import org.camunda.bpm.engine.runtime.ProcessInstance
import org.camunda.bpm.engine.test.Deployment
import org.camunda.bpm.engine.test.ProcessEngineRule
import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.runtimeService
import org.junit.Rule
import org.junit.Test
import java.util.*

@Deployment(resources = ["./workflows/model/CreateModel.bpmn"])
class CreateModelWorkflowTest {
    private val processDefinition = "CreateModel"

    @JvmField
    var processEngineRule = ProcessEngineRule()

    private fun startProcess(
        businessKey: String = "${UUID.randomUUID()}"
    ): ProcessInstance {
        return runtimeService().startProcessInstanceByKey(
            processDefinition,
            businessKey,
            mapOf("statusInPayroll" to null)
        )
    }

    @Test
    fun `Create model workflow start checking action permission as a external task` () {
        println("Checking !! \n")
        val processInstance = startProcess()

    }
}