package com.goodcode.alucard.model.presenters

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

    fun findAll(model: Model): Iterable<DocumentModel> = documentRepository.findAll(model)
}
