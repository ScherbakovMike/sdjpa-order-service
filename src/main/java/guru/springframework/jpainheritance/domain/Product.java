package guru.springframework.jpainheritance.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Transient;
import java.util.Objects;
import java.util.Set;


@Entity
public class Product extends BaseEntity {

  public void setDescription(String description) {
    this.description = description;
  }

  public void setProductStatus(ProductStatus productStatus) {
    this.productStatus = productStatus;
  }

  public void setCategories(Set<Category> categories) {
    this.categories = categories;
  }

  public String getDescription() {
    return description;
  }

  private String description;

  public Set<Category> getCategories() {
    return categories;
  }

  public ProductStatus getProductStatus() {
    return productStatus;
  }

  @Enumerated(EnumType.STRING)
  @Transient
  @Column(name = "product_status")
  private ProductStatus productStatus;

  @ManyToMany
  @JoinTable(name = "product_category",
      joinColumns = @JoinColumn(name = "product_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id"))
  private Set<Category> categories;

  private Integer quantityOnHand = 0;

  public Integer getQuantityOnHand() {
    return quantityOnHand;
  }

  public void setQuantityOnHand(Integer quantityOnHand) {
    this.quantityOnHand = quantityOnHand;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Product product)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }

    if (!Objects.equals(description, product.description)) {
      return false;
    }
    if (productStatus != product.productStatus) {
      return false;
    }
    if (!Objects.equals(categories, product.categories)) {
      return false;
    }
    return Objects.equals(quantityOnHand, product.quantityOnHand);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (description != null ? description.hashCode() : 0);
    result = 31 * result + (productStatus != null ? productStatus.hashCode() : 0);
    result = 31 * result + (categories != null ? categories.hashCode() : 0);
    result = 31 * result + (quantityOnHand != null ? quantityOnHand.hashCode() : 0);
    return result;
  }
}