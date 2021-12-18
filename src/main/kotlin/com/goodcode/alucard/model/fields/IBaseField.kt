package com.goodcode.alucard.model.fields

sealed interface IBaseField {
    fun isValidData(): Boolean
    fun isRequiredValid(): Boolean
}