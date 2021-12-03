package com.goodcode.alucard.model.presenters

import com.goodcode.alucard.model.entities.Model
import com.goodcode.alucard.model.repositories.ModelRepository
import org.springframework.stereotype.Service

@Service
class ModelPresenter(private val modelRepository: ModelRepository) {
    fun create(variables: MutableMap<String, Any>) =
        modelRepository.save(
            Model(
                name = "${variables.getValue("modelName")}"
            )
        )
}
