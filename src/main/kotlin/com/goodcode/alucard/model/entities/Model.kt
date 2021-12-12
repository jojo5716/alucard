package com.goodcode.alucard.model.entities

import com.goodcode.alucard.entities.BaseEntity
import org.hibernate.Hibernate
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "models")
data class Model(
    @Id
    @GeneratedValue
    val id: UUID? = null,

    @Column(name = "name")
    val name: String
) : BaseEntity() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        other as Model

        return this.name == other.name
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this.name
    }
}