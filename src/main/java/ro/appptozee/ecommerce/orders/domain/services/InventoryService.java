package ro.appptozee.ecommerce.orders.domain.services;

import ro.appptozee.ecommerce.orders.domain.Order;

public interface InventoryService {
    boolean isAvailable(long productId, int quantity);

    boolean reserve(Order order);

    void checkin(Order order);
}
