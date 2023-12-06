package guru.springframework.jpainheritance.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import guru.springframework.jpainheritance.domain.Customer;
import guru.springframework.jpainheritance.domain.OrderApproval;
import guru.springframework.jpainheritance.domain.OrderHeader;
import guru.springframework.jpainheritance.domain.OrderLine;
import guru.springframework.jpainheritance.domain.Product;
import guru.springframework.jpainheritance.domain.ProductStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderHeaderRepositoryTest {

  @Autowired
  OrderHeaderRepository orderHeaderRepository;

  @Autowired
  ProductRepository productRepository;

//  @Autowired
//  OrderApprovalRepository orderApprovalRepository;

  @Autowired
  CustomerRepository customerRepository;

  Product product;

  @BeforeEach
  void setUp() {
    var newProduct = new Product();
    newProduct.setProductStatus(ProductStatus.NEW);
    newProduct.setDescription("test product");
    product = productRepository.saveAndFlush(newProduct);
  }

  @Test
  void testSaveOrder() {
    OrderHeader orderHeader = new OrderHeader();
    orderHeader.setCustomer(new Customer());
    OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);

    assertNotNull(savedOrder);
    assertNotNull(savedOrder.getId());

    OrderHeader fetchedOrder = orderHeaderRepository.getReferenceById(savedOrder.getId());

    assertNotNull(fetchedOrder);
    assertNotNull(fetchedOrder.getId());
    assertNotNull(fetchedOrder.getCreatedDate());
    assertNotNull(fetchedOrder.getLastModifiedDate());
  }

  @Test
  void testSaveOrderWithLine() {
    OrderHeader orderHeader = new OrderHeader();
    orderHeader.setCustomer(new Customer());

    OrderLine orderLine = new OrderLine();
    orderLine.setQuantityOrdered(5);
    orderLine.setProduct(product);
    orderHeader.addOrderLine(orderLine);

    OrderApproval approval = new OrderApproval();
    approval.setApprovedBy("me");
//    var savedApproval = orderApprovalRepository.save(approval);
    orderHeader.setOrderApproval(approval);

    OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);
    //orderHeaderRepository.flush();

    assertNotNull(savedOrder);
    assertNotNull(savedOrder.getId());
    assertNotNull(savedOrder.getOrderLines());
    assertThat(savedOrder.getOrderLines()).hasSize(1);

    OrderHeader fetchedOrder = orderHeaderRepository.getReferenceById(savedOrder.getId());

    assertNotNull(fetchedOrder);
    assertThat(fetchedOrder.getOrderLines()).hasSize(1);
  }

  @Test
  void testDeleteCascade() {

    OrderHeader orderHeader = new OrderHeader();
    Customer customer = new Customer();
    customer.setCustomerName("new Customer");
    orderHeader.setCustomer(customerRepository.save(customer));

    OrderLine orderLine = new OrderLine();
    orderLine.setQuantityOrdered(3);
    orderLine.setProduct(product);

    OrderApproval approval = new OrderApproval();
    approval.setApprovedBy("me");
//    var savedApproval = orderApprovalRepository.save(approval);
    orderHeader.setOrderApproval(approval);

    orderHeader.addOrderLine(orderLine);
    OrderHeader savedOrder = orderHeaderRepository.saveAndFlush(orderHeader);

    System.out.println("order saved and flushed");

    orderHeaderRepository.deleteById(savedOrder.getId());
    orderHeaderRepository.flush();

    assertThrows(JpaObjectRetrievalFailureException.class, () -> {
      OrderHeader fetchedOrder = orderHeaderRepository.getById(savedOrder.getId());

      assertNull(fetchedOrder);
    });
  }
}