package ro.appptozee.ecommerce.orders.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.appptozee.ecommerce.common.dto.payment.OrderDto;
import ro.appptozee.ecommerce.orders.domain.Order;
import ro.appptozee.ecommerce.orders.domain.services.PaymentService;
import ro.appptozee.ecommerce.payment.PaymentGateway;

@Component
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentGateway paymentGateway;

    @Override
    public void createPayment(Order order, int price) {
        paymentGateway.paymentOfOrder(new OrderDto(order.getOrderId(),price));
    }
}
