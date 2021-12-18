package com.goodcode.alucard.model.fields

import camundajar.impl.com.google.gson.JsonElement
import com.goodcode.alucard.model.entities.FieldModel

class InputField(fieldModel: FieldModel, fieldValue: JsonElement?): Field(fieldModel, fieldValue), IBaseField