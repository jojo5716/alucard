package com.goodcode.alucard.model.presenters

import camundajar.impl.com.google.gson.JsonObject
import com.goodcode.alucard.model.entities.FieldModel
import com.goodcode.alucard.model.entities.Model
import com.goodcode.alucard.model.repositories.FieldRepository
import org.springframework.stereotype.Service

@Service
class FieldPresenter(private val fieldRepository: FieldRepository) {
    fun create(field: JsonObject, modelCreated: Model) =
        fieldRepository.save(
            FieldModel(
                name = field.get("name").asString,
                element = field.get("element").asString,
                type = field.get("type").asString,
                required = field.get("required").asBoolean,
                model = modelCreated
            )
        )
}
