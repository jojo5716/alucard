package com.goodcode.alucard.model.presenters

import camundajar.impl.com.google.gson.JsonElement
import com.goodcode.alucard.model.entities.DocumentModel
import com.goodcode.alucard.model.entities.FieldModel
import com.goodcode.alucard.model.entities.FieldValueModel
import com.goodcode.alucard.model.repositories.FieldValueRepository
import org.springframework.stereotype.Service

@Service
class FieldValuePresenter(private val fieldValueRepository: FieldValueRepository) {
    fun create(fieldData: JsonElement?, fieldModel: FieldModel, documentModel: DocumentModel) =
        fieldValueRepository.save(
            FieldValueModel(
                value = fieldData?.asString.toString(),
                fieldModel = fieldModel,
                documentModel = documentModel
            )
        )

    fun findByDocument(documentModel: DocumentModel) =  fieldValueRepository.findByDocument(documentModel)
}
