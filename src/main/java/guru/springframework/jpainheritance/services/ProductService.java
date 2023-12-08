package guru.springframework.jpainheritance.services;

import guru.springframework.jpainheritance.domain.Product;
import java.util.Optional;

public interface ProductService {

  Optional<Product> saveProduct(Product product);

  Product updateQOH(Long id, Integer quantityOnHand);
}
