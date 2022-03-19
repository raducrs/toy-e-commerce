package ro.appptozee.ecommerce.orders.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import ro.appptozee.ecommerce.common.dto.payment.PaymentCompleted;
import ro.appptozee.ecommerce.common.dto.payment.PaymentStatus;
import ro.appptozee.ecommerce.orders.domain.OrderService;

@Component
public class PaymentProcessing implements ApplicationListener<PaymentCompleted> {

    @Autowired
    private OrderService orderService;

    @Override
    public void onApplicationEvent(PaymentCompleted event) {
        if (event.getPaymentStatus() == PaymentStatus.PAYMENT_NOK){
           orderService.checkin(event.getOrderDto());
        } else if (event.getPaymentStatus() == PaymentStatus.PAYMENT_OK){
            orderService.completed(event.getOrderDto());
        }
    }
}
