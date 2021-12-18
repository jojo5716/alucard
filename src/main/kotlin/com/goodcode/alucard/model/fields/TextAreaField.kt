package com.goodcode.alucard.model.fields

import camundajar.impl.com.google.gson.JsonElement
import com.goodcode.alucard.model.entities.FieldModel


class TextAreaField(fieldModel: FieldModel, private val fieldValue: JsonElement?): Field(fieldModel, fieldValue), IBaseField {
    override fun isValidData(): Boolean {
        val isValidData = super.isValidData()

        return if (isValidData && fieldValue !== null) {
            isValidData && fieldValue.asString is String
        } else isValidData
    }
}