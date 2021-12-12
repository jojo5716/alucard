package com.goodcode.alucard.model.repositories

import com.goodcode.alucard.model.entities.Field
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface FieldRepository: CrudRepository<Field, UUID>