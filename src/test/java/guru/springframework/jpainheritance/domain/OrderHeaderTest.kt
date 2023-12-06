package guru.springframework.jpainheritance.domain

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class OrderHeaderTest {

    @Test
    fun equalsTrue() {
        val orderHeader1 =
            OrderHeader(1, null, null, Customer().apply { id = 1 }, null, null, null, null)
        val orderHeader2 =
            OrderHeader(1, null, null, Customer().apply { id = 1 }, null, null, null, null)
        assertTrue(orderHeader1 == orderHeader2)
    }

    @Test
    fun equalsFalse() {
        val orderHeader1 =
            OrderHeader(1, null, null, Customer().apply { id = 1 }, null, null, null, null)
        val orderHeader2 =
            OrderHeader(2, null, null, Customer().apply { id = 2 }, null, null, null, null)
        assertFalse(orderHeader1 == orderHeader2)
    }
}