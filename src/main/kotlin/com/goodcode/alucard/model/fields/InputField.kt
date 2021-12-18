package com.goodcode.alucard.model.fields

import com.goodcode.alucard.model.entities.Field
import org.springframework.stereotype.Component
import java.util.*
import javax.persistence.*

class InputField( private val field: Field): BaseField(), IBaseField