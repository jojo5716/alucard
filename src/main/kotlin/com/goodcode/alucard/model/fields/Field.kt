package com.goodcode.alucard.model.fields

import camundajar.impl.com.google.gson.JsonElement
import com.goodcode.alucard.model.entities.DocumentModel
import com.goodcode.alucard.model.entities.FieldModel
import com.goodcode.alucard.model.entities.FieldValueModel
import com.goodcode.alucard.model.presenters.FieldValuePresenter

sealed class Field(
    private val fieldModel: FieldModel,
    private val fieldValue: JsonElement?,
    private val fieldValuePresenter: FieldValuePresenter
) : IBaseField {
    override fun isValidData(): Boolean {
        val isRequiredValid = this.isRequiredValid()

        return false !in listOf(isRequiredValid)
    }

    override fun isRequiredValid(): Boolean =
        (fieldModel.required && fieldValue !== null && fieldValue.toString().isNotEmpty()) || !fieldModel.required

    override fun insertData(documentModel: DocumentModel): FieldValueModel =
        fieldValuePresenter.create(fieldValue, fieldModel, documentModel)
}