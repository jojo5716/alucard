package com.goodcode.alucard.model.fields

import camundajar.impl.com.google.gson.JsonElement
import com.goodcode.alucard.model.entities.FieldModel
import com.goodcode.alucard.model.presenters.FieldValuePresenter
import com.goodcode.alucard.model.services.FieldTypes
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class FieldLoader(private val fieldValuePresenter: FieldValuePresenter) {
    fun loadFieldByElement(
        fieldModel: FieldModel,
        fieldValue: JsonElement?
    ): Field? {
        return when (fieldModel.element) {
            FieldTypes.INPUT.fieldType -> InputField(fieldModel, fieldValuePresenter, fieldValue)
            FieldTypes.TEXT_AREA.fieldType -> TextAreaField(fieldModel, fieldValuePresenter, fieldValue)
            else -> {
                Logger.getGlobal().info("Field does not exist: $fieldModel")
                null
            }
        }
    }
}