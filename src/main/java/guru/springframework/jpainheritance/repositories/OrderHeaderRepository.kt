package guru.springframework.jpainheritance.repositories

import org.springframework.data.jpa.repository.JpaRepository
import guru.springframework.jpainheritance.domain.OrderHeader
import org.springframework.stereotype.Repository

@Repository
interface OrderHeaderRepository : JpaRepository<OrderHeader, Long> {
}