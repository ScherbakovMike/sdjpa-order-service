package guru.springframework.jpainheritance.repositories;

import guru.springframework.jpainheritance.domain.Product;
import guru.springframework.jpainheritance.domain.ProductStatus;
import guru.springframework.jpainheritance.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackageClasses = {ProductService.class})
class ProductRepositoryTest {

  @Autowired
  ProductRepository productRepository;

  @Autowired
  ProductService productService;

  @Test
  void testSaveProduct() {
    Product product = new Product();
    product.setDescription("My Product");
    product.setProductStatus(ProductStatus.NEW);

    Product savedProduct = productRepository.save(product);

    Product fetchedProduct = productRepository.getById(savedProduct.getId());

    assertNotNull(fetchedProduct);
    assertNotNull(fetchedProduct.getDescription());
    assertNotNull(fetchedProduct.getCreatedDate());
    assertNotNull(fetchedProduct.getLastModifiedDate());
  }

  @Test
  void testGetCategory() {
    var product = productRepository.findByDescription("PRODUCT1");

    assertNotNull(product.get());
    assertNotNull(product.get().getCategories());
  }

  @Test
  void addAndUpdateProduct() {
    Product product = new Product();
    product.setDescription("My Product");
    product.setProductStatus(ProductStatus.NEW);

    Product savedProduct = productService.saveProduct(product).get();

    savedProduct.setQuantityOnHand(25);

    Product savedProduct2 = productService.updateQOH(savedProduct.getId(), 25);

    System.out.println(savedProduct2.getQuantityOnHand());
  }
}