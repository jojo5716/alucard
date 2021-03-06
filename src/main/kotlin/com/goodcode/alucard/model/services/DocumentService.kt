package com.goodcode.alucard.model.services

import camundajar.impl.com.google.gson.Gson
import com.goodcode.alucard.bpm.requests.PayloadSchema
import com.goodcode.alucard.model.entities.DocumentModel
import com.goodcode.alucard.model.entities.Model
import com.goodcode.alucard.model.fields.Field
import com.goodcode.alucard.model.fields.FieldLoader
import com.goodcode.alucard.model.presenters.DocumentPresenter
import com.goodcode.alucard.model.presenters.FieldValuePresenter
import com.goodcode.alucard.model.presenters.ModelPresenter
import org.springframework.stereotype.Service

@Service
class DocumentService(
    private val documentPresenter: DocumentPresenter,
    private val modelPresenter: ModelPresenter,
    private val fieldValuePresenter: FieldValuePresenter,
    private val fieldLoader: FieldLoader
) : IDocumentService {
    override fun parseDocumentByModelFields(
        documents: Iterable<DocumentModel>,
        model: Model
    ): List<Map<String, String?>> {
        val gson = Gson()

        return documents.map { document ->
            val documentData = emptyMap<String, String?>().toMutableMap()
            val fieldValues = fieldValuePresenter.findByDocument(document)

            fieldValues.forEach { fieldValue ->
                val field: Field? = fieldLoader.loadFieldByElement(fieldValue.fieldModel, fieldValue.value)

                documentData[fieldValue.fieldModel.name] = gson.toJson(
                    PayloadSchema(
                        value = field?.value()!!,
                        type = field.valueType()
                    )
                )
            }

            documentData
        }
    }

    override fun getDocumentsFromModel(modelName: String): List<Map<String, String?>> {
        val model: Model = modelPresenter.findByName(modelName)

        return parseDocumentByModelFields(documentPresenter.findAll(model), model)
    }
}