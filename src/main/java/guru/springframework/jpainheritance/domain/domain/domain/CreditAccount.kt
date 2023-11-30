package guru.springframework.jpainheritance.domain.domain.domain

import jakarta.persistence.Entity
import java.math.BigDecimal

@Entity(name = "CreditAccount")
class CreditAccount(
    id: Long?,
    owner: String?,
    balance: BigDecimal?,
    interestRate: BigDecimal?,
    val creditLimit: BigDecimal?
) :
    Account(
        id, owner, balance,
        interestRate
    ) {
    constructor() : this(null, null, null, null, null) {

    }
}
