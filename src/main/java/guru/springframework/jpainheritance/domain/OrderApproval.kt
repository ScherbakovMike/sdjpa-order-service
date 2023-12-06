package guru.springframework.jpainheritance.domain

import jakarta.persistence.Entity
import java.sql.Timestamp

@Entity
class OrderApproval(
    id: Long?,
    createdDate: Timestamp?,
    lastModifiedDate: Timestamp?,
    val approvedBy: String?
) : BaseEntity(id, createdDate, lastModifiedDate)