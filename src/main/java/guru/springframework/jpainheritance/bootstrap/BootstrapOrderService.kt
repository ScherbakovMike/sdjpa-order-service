package guru.springframework.jpainheritance.bootstrap

import guru.springframework.jpainheritance.repositories.OrderHeaderRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
open class BootstrapOrderService (
    val orderHeaderRepository: OrderHeaderRepository
) {

    @Transactional
    open fun readOrderData() {
        val orderHeader = orderHeaderRepository.findById(1L).get()
        orderHeader.orderLines!!.forEach { ol ->
            println(ol.product!!.description)

            ol.product!!.categories.forEach { cat ->
                println(cat.description)
            }
        }
    }
}