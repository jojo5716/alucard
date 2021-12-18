package com.goodcode.alucard.model.repositories

import com.goodcode.alucard.model.entities.FieldModel
import com.goodcode.alucard.model.entities.Model
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface FieldRepository: CrudRepository<FieldModel, UUID> {
    fun findByModel(model: Model): List<FieldModel>
}