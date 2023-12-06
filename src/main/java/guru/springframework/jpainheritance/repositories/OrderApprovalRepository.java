package guru.springframework.jpainheritance.repositories;

import guru.springframework.jpainheritance.domain.OrderApproval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderApprovalRepository extends JpaRepository<OrderApproval, Long> {

}
