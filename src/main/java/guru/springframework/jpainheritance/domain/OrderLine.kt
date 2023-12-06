package guru.springframework.jpainheritance.domain

import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import java.sql.Timestamp

@Entity
class OrderLine(
    id: Long?,
    createdDate: Timestamp?,
    lastModifiedDate: Timestamp?,
    var quantityOrdered:Int?,
    @ManyToOne
    var orderHeader: OrderHeader?,
    @ManyToOne var product: Product?
): BaseEntity(id, createdDate, lastModifiedDate) {
    constructor() : this(null, null, null, null, null, null)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is OrderLine) return false
        if (!super.equals(other)) return false

        if (quantityOrdered != other.quantityOrdered) return false
        if (orderHeader != other.orderHeader) return false
        if (product != other.product) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (quantityOrdered ?: 0)
        result = 31 * result + (product?.hashCode() ?: 0)
        return result
    }

}