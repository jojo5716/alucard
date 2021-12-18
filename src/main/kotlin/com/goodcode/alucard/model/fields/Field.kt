package com.goodcode.alucard.model.fields

import camundajar.impl.com.google.gson.JsonElement
import com.goodcode.alucard.model.entities.FieldModel

sealed class Field(private val fieldModel: FieldModel, private val fieldValue: JsonElement?) : IBaseField {
    override fun isValidData(): Boolean {
        val isRequiredValid = this.isRequiredValid()

        return false !in listOf(isRequiredValid)
    }

    override fun isRequiredValid(): Boolean =
        (fieldModel.required && fieldValue.toString().isNotEmpty()) || !fieldModel.required
}