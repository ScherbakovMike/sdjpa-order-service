package guru.springframework.jpainheritance.domain

import jakarta.persistence.Embeddable

@Embeddable
class Address(
    var address: String?,
    var city: String?,
    var state: String?,
    val zipCode: String?
) {
    constructor() : this(null, null, null,null) {

    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Address

        if (address != other.address) return false
        if (city != other.city) return false
        if (state != other.state) return false
        if (zipCode != other.zipCode) return false

        return true
    }

    override fun hashCode(): Int {
        var result = address?.hashCode() ?: 0
        result = 31 * result + (city?.hashCode() ?: 0)
        result = 31 * result + (state?.hashCode() ?: 0)
        result = 31 * result + (zipCode?.hashCode() ?: 0)
        return result
    }

}