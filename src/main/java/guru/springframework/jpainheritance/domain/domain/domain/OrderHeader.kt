package guru.springframework.jpainheritance.domain.domain.domain

import jakarta.persistence.Entity

@Entity
class OrderHeader(
    id: Long?,
    val customer: String?
) : BaseEntity(id) {
    constructor() : this(null, null) {

    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is OrderHeader) return false
        if (!super.equals(other)) return false

        if (customer != other.customer) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (customer?.hashCode() ?: 0)
        return result
    }
}