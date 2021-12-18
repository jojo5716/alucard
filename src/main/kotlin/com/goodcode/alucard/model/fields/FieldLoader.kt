package com.goodcode.alucard.model.fields

import camundajar.impl.com.google.gson.JsonElement
import camundajar.impl.com.google.gson.JsonParser
import com.goodcode.alucard.model.entities.FieldModel
import com.goodcode.alucard.model.services.FieldTypes
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class FieldLoader {
    fun loadFieldByElement(fieldModel: FieldModel, fieldValue: JsonElement?): Field? {
        return when (fieldModel.element) {
            FieldTypes.INPUT.fieldType -> InputField(fieldModel, fieldValue)
            else -> {
                Logger.getGlobal().info("Field does not exist: $fieldModel")
                null
            }
        }
    }
}