package com.goodcode.alucard.model.services

import com.goodcode.alucard.bpm.requests.PayloadSchema
import com.goodcode.alucard.model.entities.DocumentModel
import com.goodcode.alucard.model.entities.Model
import com.goodcode.alucard.model.presenters.DocumentPresenter
import com.goodcode.alucard.model.presenters.FieldPresenter
import com.goodcode.alucard.model.presenters.FieldValuePresenter
import com.goodcode.alucard.model.presenters.ModelPresenter
import org.springframework.stereotype.Service

@Service
class DocumentService(
    private val documentPresenter: DocumentPresenter,
    private val modelPresenter: ModelPresenter,
    private val fieldPresenter: FieldPresenter,
    private val fieldValuePresenter: FieldValuePresenter
) : IDocumentService {
    override fun parseDocumentByModelFields(
        documents: Iterable<DocumentModel>,
        model: Model
    ): List<Map<String, PayloadSchema>> {
        val fields = fieldPresenter.findByModel(model)

        return documents.map { document ->
            val documentData = emptyMap<String, PayloadSchema>().toMutableMap()
            val fieldValues = fieldValuePresenter.findByDocument(document)
            fieldValues.forEach { fieldValue ->
                documentData[fieldValue.fieldModel.name] = PayloadSchema(
                    value = fieldValue.value,
                    type = "String")
            }
            documentData
        }
    }

    override fun getDocumentsFromModel(modelName: String): List<Map<String, PayloadSchema>> {
        val model: Model = modelPresenter.findByName(modelName)

        return parseDocumentByModelFields(documentPresenter.findAll(model), model)
    }
}