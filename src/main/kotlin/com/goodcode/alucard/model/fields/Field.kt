package com.goodcode.alucard.model.fields

import camundajar.impl.com.google.gson.JsonElement
import com.goodcode.alucard.model.entities.FieldModel

sealed class Field(private val fieldModel: FieldModel, private val fieldValue: JsonElement?) : IBaseField {
    override fun isValidData(): Boolean {
        val isRequiredValid = this.isRequiredValid()

        return false !in listOf(isRequiredValid)
    }

    override fun isRequiredValid(): Boolean {
        println("\n\n fieldModel: $fieldModel")
        println("value: $fieldValue")
        println("is valid: ${((fieldModel.required && !fieldValue?.isJsonNull!!) || !fieldModel.required)} \n\n\n")

        return (fieldModel.required && !fieldValue?.isJsonNull!!) || !fieldModel.required
    }
}