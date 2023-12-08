package guru.springframework.jpainheritance.domain

import jakarta.persistence.*
import java.sql.Timestamp
import java.util.Objects

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
open class OrderHeader(
    id: Long?,
    createdDate: Timestamp?,
    lastModifiedDate: Timestamp?,
    @ManyToOne(cascade = [CascadeType.PERSIST])
    open var customer: Customer?,
    @Embedded val shippingAddress: Address?,
    @Embedded val billToAddress: Address?,
    @Enumerated(EnumType.STRING) val orderStatus: OrderStatus?,
    @OneToMany(
        mappedBy = "orderHeader",
        cascade = [CascadeType.PERSIST],
        orphanRemoval = true
    ) var orderLines: MutableSet<OrderLine>?
    ) : BaseEntity(id, createdDate, lastModifiedDate) {

    @OneToOne(cascade = [CascadeType.PERSIST, CascadeType.REMOVE], mappedBy = "orderHeader")
    var orderApproval: OrderApproval? = null
        set(value) {
            value?.let { it.orderHeader = this }
            field = value
        }

    constructor() : this(null, null, null, null, null, null, null, null) {

    }

    fun addOrderLine(line: OrderLine) {
        if (Objects.isNull(this.orderLines)) {
            orderLines = mutableSetOf()
        }
        this.orderLines!!.add(line)
        line.orderHeader = this
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is OrderHeader) return false
        if (!super.equals(other)) return false

        if (customer != other.customer) return false
        if (shippingAddress != other.shippingAddress) return false
        if (billToAddress != other.billToAddress) return false
        if (orderStatus != other.orderStatus) return false
        if (orderApproval != other.orderApproval) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (customer?.hashCode() ?: 0)
        result = 31 * result + (shippingAddress?.hashCode() ?: 0)
        result = 31 * result + (billToAddress?.hashCode() ?: 0)
        result = 31 * result + (orderStatus?.hashCode() ?: 0)
        result = 31 * result + (orderApproval?.hashCode() ?: 0)
        return result
    }


}