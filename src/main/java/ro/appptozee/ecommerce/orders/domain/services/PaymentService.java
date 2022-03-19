package ro.appptozee.ecommerce.orders.domain.services;

import ro.appptozee.ecommerce.orders.domain.Order;

public interface PaymentService {
    void createPayment(Order order, int price);
}
