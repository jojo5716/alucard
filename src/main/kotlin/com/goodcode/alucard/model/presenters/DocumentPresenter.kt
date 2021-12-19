package com.goodcode.alucard.model.presenters

import camundajar.impl.com.google.gson.JsonElement
import com.goodcode.alucard.model.entities.DocumentModel
import com.goodcode.alucard.model.entities.Model
import com.goodcode.alucard.model.repositories.DocumentRepository
import org.springframework.stereotype.Service

@Service
class DocumentPresenter(private val documentRepository: DocumentRepository) {
    fun create(model: Model) =
        documentRepository.save(
            DocumentModel(
                model = model
            )
        )
}
