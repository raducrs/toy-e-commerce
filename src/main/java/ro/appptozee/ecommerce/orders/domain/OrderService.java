package ro.appptozee.ecommerce.orders.domain;

import ro.appptozee.ecommerce.common.dto.payment.OrderDto;
import ro.appptozee.ecommerce.orders.domain.services.InventoryService;
import ro.appptozee.ecommerce.orders.domain.services.PaymentService;
import ro.appptozee.ecommerce.orders.domain.services.PricingService;

import java.util.Optional;

public class OrderService {
    private final InventoryService inventoryService;
    private final OrderRepository orderRepository;
    private final PricingService pricingService;
    private final PaymentService paymentService;


    public OrderService(InventoryService inventoryService, OrderRepository orderRepository, PricingService pricingService, PaymentService paymentService) {
        this.inventoryService = inventoryService;
        this.orderRepository = orderRepository;
        this.pricingService = pricingService;
        this.paymentService = paymentService;
    }

    public boolean checkout(long orderId){
        var opt = orderRepository.findById(orderId);
        return opt
                .map(order -> {
                    order.setOrderRepository(orderRepository);
                    order.setInventoryService(inventoryService);
                    order.setPricingService(pricingService);
                    order.setPaymentService(paymentService);
                return order;})
                .map(Order::checkout)
                .orElse(false);
    }

    public StockAction addToCart(long userId, long productId){
        Optional<Order> optOrder = orderRepository.findOrderForUser(userId);
        var order = optOrder.orElseGet(()->orderRepository.createCart(userId));
        order.setInventoryService(inventoryService);
        return new StockAction(order.addToCart(productId), order.getOrderId());
    }

    public void checkin(OrderDto orderDto){
        var order = orderRepository.findById(orderDto.orderId()).get();
        order.setInventoryService(inventoryService);
        order.checkin();
    }


    public void completed(OrderDto orderDto) {
        var order = orderRepository.findById(orderDto.orderId()).get();
        order.setOrderStatus(OrderStatus.PAYMENT_SUCCEEDED);
        orderRepository.saveOrder(order);
    }
}
