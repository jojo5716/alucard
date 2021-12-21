package com.goodcode.alucard.model.fields

import com.goodcode.alucard.model.entities.FieldModel
import com.goodcode.alucard.model.presenters.FieldValuePresenter


class TextAreaField(
    fieldModel: FieldModel,
    fieldValuePresenter: FieldValuePresenter,
    private val fieldValue: String?
) : Field(fieldModel, fieldValue, fieldValuePresenter),
    IBaseField {
    override fun isValidData(): Boolean {
        val isValidData = super.isValidData()

        return if (isValidData && fieldValue !== null) {
            isValidData && fieldValue is String
        } else isValidData
    }
}