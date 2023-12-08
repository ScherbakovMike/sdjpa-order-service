package guru.springframework.jpainheritance.bootstrap

import guru.springframework.jpainheritance.domain.Customer
import guru.springframework.jpainheritance.repositories.CustomerRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
open class Bootstrap(
    val bootstrapOrderService: BootstrapOrderService,
    val customerRepository: CustomerRepository
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        bootstrapOrderService.readOrderData()

        val customer = Customer().apply {
            customerName = "Testing Version"
        }
        val savedCustomer = customerRepository.save(customer)
        println("Version is: ${savedCustomer.version}")

        savedCustomer.apply {
            customerName = "Testing Version 2"
        }
        val savedCustomer2 = customerRepository.save(savedCustomer)
        println("Version is: ${savedCustomer2.version}")

        savedCustomer2.apply {
            customerName = "Testing Version 3"
        }
        val savedCustomer3 = customerRepository.save(savedCustomer2)
        println("Version is: ${savedCustomer3.version}")

        customerRepository.deleteById(savedCustomer.id!!)
    }
}