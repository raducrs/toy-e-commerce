package ro.appptozee.ecommerce.orders.domain;

import ro.appptozee.ecommerce.orders.domain.Order;

import java.util.Optional;

public interface OrderRepository {
    Optional<Order> findById(Long orderId);
    void saveOrder(Order order);

    Optional<Order> findOrderForUser(long userId);

    Order createCart(long userId);
}
