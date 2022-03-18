package ro.appptozee.ecommerce.orders.infra;

import org.springframework.context.ApplicationListener;
import ro.appptozee.ecommerce.common.dto.payment.PaymentCompleted;

public class PaymentProcessing implements ApplicationListener<PaymentCompleted> {
    @Override
    public void onApplicationEvent(PaymentCompleted event) {

    }
}
