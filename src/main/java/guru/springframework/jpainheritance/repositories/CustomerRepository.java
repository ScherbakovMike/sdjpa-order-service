package guru.springframework.jpainheritance.repositories;

import guru.springframework.jpainheritance.domain.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

  Optional<Customer> findCustomerByCustomerNameIgnoreCase(String customerName);
}