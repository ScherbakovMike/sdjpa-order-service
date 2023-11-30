package guru.springframework.jpainheritance.domain.domain.domain;

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class OrderHeaderTest {

    @Test
    fun equalsTrue() {
        val orderHeader1 = OrderHeader(1, "customer1")
        val orderHeader2 = OrderHeader(1, "customer1")
        assertTrue(orderHeader1 == orderHeader2)
    }

    @Test
    fun equalsFalse() {
        val orderHeader1 = OrderHeader(1, "customer1")
        val orderHeader2 = OrderHeader(2, "customer1")
        assertFalse(orderHeader1 == orderHeader2)
    }
}