package guru.springframework.jpainheritance.services;

import guru.springframework.jpainheritance.domain.Product;
import guru.springframework.jpainheritance.repositories.ProductRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements
    ProductService {

  private final ProductRepository productRepository;

  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public Optional<Product> saveProduct(Product product) {
    return Optional.of(productRepository.saveAndFlush(product));
  }

  @Override
  public Product updateQOH(Long id, Integer quantityOnHand) {
    var product = productRepository.findById(id).orElseThrow();
    product.setQuantityOnHand(quantityOnHand);
    return productRepository.saveAndFlush(product);
  }
}
