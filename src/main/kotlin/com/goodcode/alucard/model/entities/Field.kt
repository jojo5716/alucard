package com.goodcode.alucard.model.entities

import com.goodcode.alucard.entities.BaseEntity
import org.hibernate.Hibernate
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "fields")
data class Field(
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

    @OneToOne(targetEntity = Model::class)
    @JoinColumn(name = "models_id", referencedColumnName = "id")
    var model: Model
) : BaseEntity() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Field

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(name = $name , model = $model )"
    }
}