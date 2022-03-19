package ro.appptozee.ecommerce.orders.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.appptozee.ecommerce.orders.domain.OrderRepository;
import ro.appptozee.ecommerce.orders.domain.OrderService;
import ro.appptozee.ecommerce.orders.domain.services.InventoryService;
import ro.appptozee.ecommerce.orders.domain.services.PaymentService;
import ro.appptozee.ecommerce.orders.domain.services.PricingService;

@Configuration
public class BeanConfiguration {
    @Bean
    public OrderService orderService(InventoryService inventoryService, OrderRepository orderRepository,
                                     PricingService pricingService, PaymentService paymentService){
        return new OrderService(inventoryService,orderRepository,pricingService,paymentService);
    }
}
