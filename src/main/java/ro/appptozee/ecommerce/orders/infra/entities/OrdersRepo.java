package ro.appptozee.ecommerce.orders.infra.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ro.appptozee.ecommerce.orders.domain.Order;
import ro.appptozee.ecommerce.orders.domain.OrderRepository;
import ro.appptozee.ecommerce.orders.domain.OrderStatus;

import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class OrdersRepo implements OrderRepository {

    @Autowired
    private OrdersJpaRepository jpaRepository;

    @Autowired
    private LineItemRepository lineItemRepository;

    @Override
    public Optional<Order> findById(Long orderId) {
        return jpaRepository.findById(orderId)
                .map(this::convertFromDao);
    }



    @Override
    public void saveOrder(Order order) {
        var orderDao = jpaRepository.save(convertToDao(order));
        order.getItems().entrySet().stream()
                .map(e-> new LineItem(new LineItemPK(orderDao,e.getKey()),e.getValue()))
                .forEach(lineItemRepository::save);

    }

    @Override
    public Optional<Order> findOrderForUser(long userId) {
        return jpaRepository.findCart(userId).map(this::convertFromDao);
    }

    @Override
    public Order createCart(long userId) {
        var order = new ro.appptozee.ecommerce.orders.infra.entities.Order();
        order.setUserId(userId);
        order.setOrderStatus(OrderStatus.IN_CART);
        return convertFromDao(jpaRepository.save(order));
    }

    private Order convertFromDao(ro.appptozee.ecommerce.orders.infra.entities.Order order){
        return Order.builder()
                .orderId(order.getId())
                .orderStatus(order.getOrderStatus())
                .userId(order.getUserId())
                .items(order.getOrderProducts().stream()
                        .collect(Collectors.toMap(e->e.getId().getProductId(),LineItem::getQuantity))
                ).build();
    }

    private ro.appptozee.ecommerce.orders.infra.entities.Order  convertToDao(Order order){
        var dao = new ro.appptozee.ecommerce.orders.infra.entities.Order();
        dao.setId(order.getOrderId());
        dao.setOrderStatus(order.getOrderStatus());
        dao.setUserId(order.getUserId());
        /*dao.setOrderProducts(order.getItems().entrySet().stream()
                        .map(e-> new LineItem(new LineItemPK(dao,e.getKey()),e.getValue()))
                        .collect(Collectors.toList())


        );*/
        return dao;
    }
}
