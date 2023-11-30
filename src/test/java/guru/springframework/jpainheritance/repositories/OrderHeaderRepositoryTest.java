package guru.springframework.jpainheritance.repositories;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import guru.springframework.jpainheritance.domain.OrderHeader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderHeaderRepositoryTest {

  @Autowired
  OrderHeaderRepository orderHeaderRepository;

  @Test
  void testSaveOrder() {
    OrderHeader orderHeader = new OrderHeader();
    orderHeader.setCustomer("New Customer");
    OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);

    assertNotNull(savedOrder);
    assertNotNull(savedOrder.getId());

    OrderHeader fetchedOrder = orderHeaderRepository.getReferenceById(savedOrder.getId());

    assertNotNull(fetchedOrder);
    assertNotNull(fetchedOrder.getId());
    assertNotNull(fetchedOrder.getCreatedDate());
    assertNotNull(fetchedOrder.getLastModifiedDate());
  }
}