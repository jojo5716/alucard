package com.goodcode.alucard.model.entities

import com.goodcode.alucard.entities.BaseEntity
import org.hibernate.Hibernate
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "field_values")
data class FieldValueModel(
    @Id
    @GeneratedValue
    val id: UUID? = null,

    @OneToOne(targetEntity = FieldModel::class)
    @JoinColumn(name = "field_id", referencedColumnName = "id")
    var fieldModel: FieldModel,

    @Column(name = "value")
    val value: String
) : BaseEntity() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as FieldValueModel

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(value = $value , field = $fieldModel )"
    }
}