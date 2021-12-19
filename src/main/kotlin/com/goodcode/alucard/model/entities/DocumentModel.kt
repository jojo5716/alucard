package com.goodcode.alucard.model.entities

import com.goodcode.alucard.entities.BaseEntity
import org.hibernate.Hibernate
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "documents")
data class DocumentModel(
    @Id
    @GeneratedValue
    val id: UUID? = null,

    @OneToOne(targetEntity = Model::class)
    @JoinColumn(name = "models_id", referencedColumnName = "id")
    var model: Model
) : BaseEntity() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as DocumentModel

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(model = $model )"
    }
}