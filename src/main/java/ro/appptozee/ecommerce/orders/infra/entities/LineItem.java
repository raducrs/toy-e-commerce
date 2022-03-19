package ro.appptozee.ecommerce.orders.infra.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "line_item")
@Data
@AllArgsConstructor
public class LineItem {

    @EmbeddedId
    LineItemPK id;

    private int quantity;

    public LineItem(LineItemPK id, Integer q) {
        this.id = id;
        this.quantity = q;
    }
}