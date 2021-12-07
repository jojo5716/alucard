package com.goodcode.alucard.model.fields

import org.springframework.stereotype.Component
import java.util.*
import javax.persistence.*

@Component
class InputField(
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
    val required: Boolean
): BaseField(), IBaseField