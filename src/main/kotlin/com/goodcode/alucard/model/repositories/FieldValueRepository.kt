package com.goodcode.alucard.model.repositories

import com.goodcode.alucard.model.entities.DocumentModel
import com.goodcode.alucard.model.entities.FieldValueModel
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface FieldValueRepository: CrudRepository<FieldValueModel, UUID> {
    @Query("FROM FieldValueModel fieldValue WHERE fieldValue.documentModel=:documentModel")
    fun findByDocument(@Param("documentModel") documentModel: DocumentModel): Iterable<FieldValueModel>
}