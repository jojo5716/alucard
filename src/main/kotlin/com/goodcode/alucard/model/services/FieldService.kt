package com.goodcode.alucard.model.services

import com.goodcode.alucard.model.entities.Field
import com.goodcode.alucard.model.fields.BaseField
import com.goodcode.alucard.model.fields.InputField
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class FieldService {
    fun loadFieldByElement(field: Field): BaseField? {
        return when (field.element) {
            FieldTypes.INPUT.fieldType -> InputField(field)
            else -> {
                Logger.getGlobal().info("Field does not exist: $field")
                null
            }

        }
    }
}