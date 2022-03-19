package ro.appptozee.ecommerce.orders.infra.entities;

import lombok.Data;
import ro.appptozee.ecommerce.orders.domain.OrderStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "id.order", cascade = CascadeType.PERSIST)
    private List<LineItem> orderProducts = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
