package com.goodcode.alucard.model.repositories

import com.goodcode.alucard.model.entities.DocumentModel
import com.goodcode.alucard.model.entities.Model
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DocumentRepository : CrudRepository<DocumentModel, UUID> {
    @Query("FROM DocumentModel document WHERE document.model=:model")
    fun findAll(@Param("model") model:Model): Iterable<DocumentModel>
}