package com.goodcode.alucard.model.services

import com.goodcode.alucard.model.entities.DocumentModel
import com.goodcode.alucard.model.entities.Model

interface IDocumentService {
    fun parseDocumentByModelFields(
        documents: Iterable<DocumentModel>,
        model: Model
    ): List<Map<String, String?>>

    fun getDocumentsFromModel(modelName: String): Any
}