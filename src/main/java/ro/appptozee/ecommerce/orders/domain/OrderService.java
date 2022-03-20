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
        var order = orderRepository.findById(orderId).get();
        // total price for order (assuming individual prices were shown and discounts deducted)
        int price = pricingService.computePrice(order);

        boolean isInStock = inventoryService.reserve(order);
        if(!isInStock) return false;

        // update status
        order.checkout() ;
        orderRepository.saveOrder(order);

        // create payment with order id as reference
        paymentService.createPayment(order,price);
        return true;
    }

    public StockAction addToCart(long userId, long productId){
        // retrieve open order for user or create a new one
        Optional<Order> optOrder = orderRepository.findOrderForUser(userId);
        var order = optOrder.orElseGet(()->orderRepository.createCart(userId));

        int count = order.totalInOrder(productId);
        boolean isAvailable = inventoryService.isAvailable(productId,count+1);
        if(isAvailable){
            order.addToCart(productId);
            orderRepository.saveOrder(order);
        }
        return new StockAction(isAvailable, order.getOrderId());
    }

    public void checkin(OrderDto orderDto){
        var order = orderRepository.findById(orderDto.orderId()).get();
        inventoryService.checkin(order);
        order.checkin();
        orderRepository.saveOrder(order);
    }


    public void completed(OrderDto orderDto) {
        var order = orderRepository.findById(orderDto.orderId()).get();
        order.completed();
        orderRepository.saveOrder(order);
    }
}
