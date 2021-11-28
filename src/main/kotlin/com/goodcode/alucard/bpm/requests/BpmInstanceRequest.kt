package com.goodcode.alucard.bpm.requests

data class BpmInstanceRequest(
    val businessKey: String,
    val variables: Map<String, PayloadSchema>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BpmInstanceRequest

        if (businessKey != other.businessKey) return false

        return true
    }

    override fun hashCode(): Int {
        return businessKey.hashCode()
    }
}