package ro.appptozee.ecommerce.pricing.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name="pricing_product")
@Data
@NoArgsConstructor
public class PricingProduct {
    @Id
    private long productId;
    private int price;
}
