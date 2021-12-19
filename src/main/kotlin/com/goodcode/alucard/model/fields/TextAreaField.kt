package com.goodcode.alucard.model.fields

import camundajar.impl.com.google.gson.JsonElement
import com.goodcode.alucard.model.entities.FieldModel
import com.goodcode.alucard.model.presenters.FieldValuePresenter


class TextAreaField(
    fieldModel: FieldModel,
    fieldValuePresenter: FieldValuePresenter,
    private val fieldValue: JsonElement?
) : Field(fieldModel, fieldValue, fieldValuePresenter),
    IBaseField {
    override fun isValidData(): Boolean {
        val isValidData = super.isValidData()

        return if (isValidData && fieldValue !== null) {
            isValidData && fieldValue.asString is String
        } else isValidData
    }
}