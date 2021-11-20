package com.goodcode.alucard.bpm.templates

data class CreateModelTemplate(
    val businessKey: String,
    val variables: Map<String, Any>?
) : TaskTemplate() {

}
