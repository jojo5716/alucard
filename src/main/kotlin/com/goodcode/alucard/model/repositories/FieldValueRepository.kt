package com.goodcode.alucard.model.repositories

import com.goodcode.alucard.model.entities.FieldValueModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface FieldValueRepository: CrudRepository<FieldValueModel, UUID>