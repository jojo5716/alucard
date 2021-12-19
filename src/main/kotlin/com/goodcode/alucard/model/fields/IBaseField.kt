package com.goodcode.alucard.model.fields

import com.goodcode.alucard.model.entities.DocumentModel
import com.goodcode.alucard.model.entities.FieldValueModel

sealed interface IBaseField {
    fun isValidData(): Boolean
    fun isRequiredValid(): Boolean
    fun insertData(documentModel: DocumentModel): FieldValueModel
}