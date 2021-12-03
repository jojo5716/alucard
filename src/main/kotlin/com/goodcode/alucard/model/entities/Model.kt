package com.goodcode.alucard.model.entities

import com.goodcode.alucard.entities.BaseEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "models")
data class Model(
    @Id
    @GeneratedValue
    val id: UUID? = null,

    @Column(name = "name")
    val name: String
) : BaseEntity()