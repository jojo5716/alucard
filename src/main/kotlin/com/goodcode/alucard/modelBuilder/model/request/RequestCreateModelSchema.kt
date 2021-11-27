package com.goodcode.alucard.modelBuilder.model.request

data class RequestCreateModelVariablesSchema(
    val action: RequestVariableSchema,
    val modelName: RequestVariableSchema
)

data class RequestCreateModelSchema(
    val businessKey: String,
    val variables: RequestCreateModelVariablesSchema
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RequestCreateModelSchema

        if (businessKey != other.businessKey) return false

        return true
    }

    override fun hashCode(): Int {
        return businessKey.hashCode()
    }
}