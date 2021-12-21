package com.goodcode.alucard.model.services

import com.goodcode.alucard.bpm.requests.PayloadSchema
import com.goodcode.alucard.model.entities.DocumentModel
import com.goodcode.alucard.model.entities.Model

interface IDocumentService {
    fun parseDocumentByModelFields(
        documents: Iterable<DocumentModel>,
        model: Model
    ): Any

    fun getDocumentsFromModel(modelName: String): Any
}