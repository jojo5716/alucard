package com.goodcode.alucard.model.fields

import camundajar.impl.com.google.gson.JsonElement

sealed interface IBaseField {
    fun isValidData(): Boolean
    fun isRequiredValid(): Boolean
}