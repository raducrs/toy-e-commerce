package ro.appptozee.ecommerce.orders.infra.entities;

import lombok.Data;
import ro.appptozee.ecommerce.orders.domain.OrderStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "id.order")
    private List<LineItem> orderProducts = new ArrayList<>();

}
