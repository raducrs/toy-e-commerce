package ro.appptozee.ecommerce.orders.domain.services;

import ro.appptozee.ecommerce.orders.domain.Order;

public interface PricingService {
    int computePrice(Order order);
}
