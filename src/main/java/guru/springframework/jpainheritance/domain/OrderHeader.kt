package guru.springframework.jpainheritance.domain

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@AttributeOverrides(
    value = [
        AttributeOverride(
            name = "shippingAddress.address",
            column = Column(name = "shipping_address")
        ),
        AttributeOverride(
            name = "shippingAddress.city",
            column = Column(name = "shipping_city")
        ),
        AttributeOverride(
            name = "shippingAddress.state",
            column = Column(name = "shipping_state")
        ),
        AttributeOverride(
            name = "shippingAddress.zipCode",
            column = Column(name = "shipping_zip_code")
        ),
        AttributeOverride(
            name = "billToAddress.address",
            column = Column(name = "bill_to_address")
        ),
        AttributeOverride(
            name = "billToAddress.city",
            column = Column(name = "bill_to_city")
        ),
        AttributeOverride(
            name = "billToAddress.state",
            column = Column(name = "bill_to_state")
        ),
        AttributeOverride(
            name = "billToAddress.zipCode",
            column = Column(name = "bill_to_zip_code")
        )
    ]
)
class OrderHeader(
    id: Long?,
    createdDate: Timestamp?,
    lastModifiedDate: Timestamp?,
    var customer: String?,
    @Embedded val shippingAddress: Address?,
    @Embedded val billToAddress: Address?,
    @Enumerated(EnumType.STRING) val orderStatus: OrderStatus?
) : BaseEntity(id, createdDate, lastModifiedDate) {
    constructor() : this(null, null, null, null, null, null, null) {

    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as OrderHeader

        if (customer != other.customer) return false
        if (shippingAddress != other.shippingAddress) return false
        if (billToAddress != other.billToAddress) return false
        if (orderStatus != other.orderStatus) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (customer?.hashCode() ?: 0)
        result = 31 * result + (shippingAddress?.hashCode() ?: 0)
        result = 31 * result + (billToAddress?.hashCode() ?: 0)
        result = 31 * result + (orderStatus?.hashCode() ?: 0)
        return result
    }

}