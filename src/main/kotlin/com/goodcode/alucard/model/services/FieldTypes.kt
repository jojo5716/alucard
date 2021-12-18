package com.goodcode.alucard.model.services

interface IFieldTypes {
    val fieldType: String
}

enum class FieldTypes(override val fieldType: String) : IFieldTypes {
    INPUT(fieldType = "input"),
    TEXT_AREA(fieldType = "textarea")
}