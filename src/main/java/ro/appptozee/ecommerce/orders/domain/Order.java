package ro.appptozee.ecommerce.orders.domain;




import lombok.Builder;
import lombok.Data;
import ro.appptozee.ecommerce.orders.domain.services.InventoryService;
import ro.appptozee.ecommerce.orders.domain.services.PaymentService;
import ro.appptozee.ecommerce.orders.domain.services.PricingService;

import java.util.Map;

@Data
@Builder
public class Order {
    private Long orderId;
    private long userId;
    private OrderStatus orderStatus;
    private Map<Long,Integer> items;

    private InventoryService inventoryService;
    private OrderRepository orderRepository;
    private PricingService pricingService;
    private PaymentService paymentService;


    boolean addToCart(long productId){
        int count = 1;
        if (items.containsKey(productId)){
           count = items.get(productId) + 1;
        }
        boolean isAvailable = inventoryService.isAvailable(productId,count+1);
        if(isAvailable){
            items.put(productId,count+1);
            orderRepository.saveOrder(this);
            return true;
        }
        return false;
    }

    boolean checkout(){
        // total price for order (assuming individual prices were shown and discounts deducted)
        int price = pricingService.computePrice(this);
        boolean isInStock = inventoryService.reserve(this);
        if(!isInStock) return false;
        // update status
        orderStatus = OrderStatus.CHECKEDOUT;
        orderRepository.saveOrder(this);
        paymentService.createPayment(this,price);

        return true;
    }

    void checkin(){
        inventoryService.checkin(this);
    }
}
