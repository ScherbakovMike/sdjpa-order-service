package guru.springframework.jpainheritance.domain

import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import java.sql.Timestamp

@Entity
class OrderApproval(
    id: Long?,
    createdDate: Timestamp?,
    lastModifiedDate: Timestamp?,
    var approvedBy: String?,
    @OneToOne @JoinColumn(name = "order_header_id") var orderHeader: OrderHeader?
) : BaseEntity(id, createdDate, lastModifiedDate) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is OrderApproval) return false
        if (!super.equals(other)) return false

        if (approvedBy != other.approvedBy) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (approvedBy?.hashCode() ?: 0)
        return result
    }
}