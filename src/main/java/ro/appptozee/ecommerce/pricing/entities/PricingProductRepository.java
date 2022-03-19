package ro.appptozee.ecommerce.pricing.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricingProductRepository extends JpaRepository<PricingProduct,Long> {
}
