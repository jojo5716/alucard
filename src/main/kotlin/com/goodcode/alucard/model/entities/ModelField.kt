package com.goodcode.alucard.model.entities

import com.goodcode.alucard.entities.BaseEntity
import com.goodcode.alucard.model.fields.BaseField
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "models")
data class ModelField(
    @Id
    @GeneratedValue
    val id: UUID? = null,

    @Column(name = "name")
    val name: String,

    @Column(name = "element")
    val element: String,

    @Column(name = "type")
    val type: String,

    @Column(name = "required")
    val required: Boolean,

    @OneToOne(cascade = [PERSIST])
) : BaseEntity()