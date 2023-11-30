package guru.springframework.jpainheritance.domain.domain

import jakarta.persistence.*
import java.math.BigDecimal

@MappedSuperclass
open class Account(
    @Id val id: Long?,
    val owner: String?,
    val balance: BigDecimal?,
    val interestRate: BigDecimal?
) {
    constructor() : this(null, null, null, null) {

    }
}