package ro.appptozee.ecommerce.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ro.appptozee.ecommerce.common.dto.payment.OrderDto;
import ro.appptozee.ecommerce.common.dto.payment.PaymentCompleted;
import ro.appptozee.ecommerce.common.dto.payment.PaymentStatus;

import java.util.concurrent.CompletableFuture;

@Service
public class PaymentGateway {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void paymentOfOrder(OrderDto order){
        CompletableFuture.supplyAsync(()-> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return order;
        })
                .thenAccept(o -> // simulate async processing
                applicationEventPublisher.publishEvent(PaymentCompleted.builder()
                        .orderDto(o)
                        .paymentStatus(Math.random() < 0.5 ? PaymentStatus.PAYMENT_OK : PaymentStatus.PAYMENT_NOK))
        );
    }
}
