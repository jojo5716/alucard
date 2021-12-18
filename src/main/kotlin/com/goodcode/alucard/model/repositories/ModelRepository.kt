package com.goodcode.alucard.model.repositories

import com.goodcode.alucard.model.entities.Model
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ModelRepository: CrudRepository<Model, UUID> {
    fun existsByName(modelName: String): Boolean
    fun findByName(modelName: String): Model
}