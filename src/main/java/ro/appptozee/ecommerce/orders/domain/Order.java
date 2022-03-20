package ro.appptozee.ecommerce.orders.domain;

import lombok.Builder;
import lombok.Data;


import java.util.Map;

@Data
@Builder
public class Order {
    private Long orderId;
    private Long userId;
    private OrderStatus orderStatus;
    private Map<Long,Integer> items;


    int totalInOrder(long productId){
        return items.getOrDefault(productId, 0);
    }

    void addToCart(long productId){
        int count = 0;
        if (items.containsKey(productId)){
           count = items.get(productId);
        }
        items.put(productId,count+1);

    }

    void checkout(){
        orderStatus = OrderStatus.CHECKEDOUT;
    }

    void checkin(){
        orderStatus = OrderStatus.IN_CART;
    }

    public void completed() {
        orderStatus =OrderStatus.PAYMENT_SUCCEEDED;
    }
}
