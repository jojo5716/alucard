package com.goodcode.alucard.model.presenters

import com.goodcode.alucard.bpm.responses.FetchAndLockVariables
import com.goodcode.alucard.model.entities.Model
import com.goodcode.alucard.model.repositories.ModelRepository
import org.springframework.stereotype.Service

@Service
class ModelPresenter(private val modelRepository: ModelRepository) {
    fun create(variables: Map<String, FetchAndLockVariables>) =
        modelRepository.save(
            Model(
                name = "${variables["modelName"]?.value}"
            )
        )
}
