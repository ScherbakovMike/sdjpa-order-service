package guru.springframework.jpainheritance.repositories;

import guru.springframework.jpainheritance.domain.Product;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

/**
 * Created by jt on 12/11/21.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

  Optional<Product> findByDescription(String description);

  @Override
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  Optional<Product> findById(Long aLong);
}