package ro.appptozee.ecommerce.orders.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.appptozee.ecommerce.common.dto.inventory.LineItem;
import ro.appptozee.ecommerce.common.dto.inventory.OrderDto;
import ro.appptozee.ecommerce.orders.domain.Order;
import ro.appptozee.ecommerce.orders.domain.services.InventoryService;

import java.util.stream.Collectors;

@Component
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private ro.appptozee.ecommerce.inventory.service.InventoryService inventoryService;

    @Override
    public boolean isAvailable(long productId, int quantity) {
        return inventoryService.isInStock(productId,quantity);
    }

    @Override
    public boolean reserve(Order order) {
        try{
            OrderDto orderDto = getOrderDto(order);
            inventoryService.reserve(orderDto);
            return true;
        } catch (Exception ex){
            return false;
        }
    }



    @Override
    public void checkin(Order order) {
        inventoryService.checkin(getOrderDto(order));
    }

    private OrderDto getOrderDto(Order order) {
        var lineItems = order.getItems().entrySet().stream()
                .map(kv->new LineItem(kv.getKey(),kv.getValue()))
                .collect(Collectors.toList());
        return new OrderDto(lineItems);
    }
}
