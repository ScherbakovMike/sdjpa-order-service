package guru.springframework.jpainheritance.domain.domain

import jakarta.persistence.Entity
import java.math.BigDecimal

@Entity(name = "DebitAccount")
class DebitAccount(
    id: Long?,
    owner: String?,
    balance: BigDecimal?,
    interestRate: BigDecimal?,
    val overDraftFee: BigDecimal?
) :
    Account(
        id, owner, balance,
        interestRate
    ) {
    constructor() : this(null, null, null, null, null) {

    }
}
