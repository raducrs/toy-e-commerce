package ro.appptozee.ecommerce.inventory.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name="inventory_product")
@Data
public class InventoryProduct {
    @Id
    private long productId;
    private String name;
    private int quantity;
}
