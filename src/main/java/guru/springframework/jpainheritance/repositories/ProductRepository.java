package guru.springframework.jpainheritance.repositories;

import guru.springframework.jpainheritance.domain.Product;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jt on 12/11/21.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

  Optional<Product> findByDescription(String description);
}