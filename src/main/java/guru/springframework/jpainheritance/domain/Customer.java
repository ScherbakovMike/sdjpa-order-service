package guru.springframework.jpainheritance.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

@Getter @Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {

  private String customerName;

  @Embedded
  private Address address;

  private String phone;
  private String email;

  @OneToMany(mappedBy = "customer")
  private Set<OrderHeader> orders = new LinkedHashSet<>();

  @Override
  public final boolean equals(Object object) {

    if (this == object) {
      return true;
    }
    if (object == null) {
      return false;
    }
    Class<?> oEffectiveClass = object instanceof HibernateProxy
        ? ((HibernateProxy) object).getHibernateLazyInitializer()
        .getPersistentClass() : object.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy
        ? ((HibernateProxy) this).getHibernateLazyInitializer()
        .getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) {
      return false;
    }
    Customer customer = (Customer) object;
    return getId() != null && Objects.equals(getId(), customer.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer()
        .getPersistentClass().hashCode() : getClass().hashCode();
  }
}