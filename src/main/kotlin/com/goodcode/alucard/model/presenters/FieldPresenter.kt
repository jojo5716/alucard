package com.goodcode.alucard.model.presenters

import camundajar.impl.com.google.gson.JsonObject
import com.goodcode.alucard.model.entities.Field
import com.goodcode.alucard.model.entities.Model
import com.goodcode.alucard.model.repositories.FieldRepository
import org.springframework.stereotype.Service

@Service
class FieldPresenter(private val fieldRepository: FieldRepository) {
    fun create(field: JsonObject, modelCreated: Model) =
        fieldRepository.save(
            Field(
                name = field.get("name").toString(),
                element = field.get("element").toString(),
                type = field.get("type").toString(),
                required = field.get("required").asBoolean,
                model = modelCreated
            )
        )
}
