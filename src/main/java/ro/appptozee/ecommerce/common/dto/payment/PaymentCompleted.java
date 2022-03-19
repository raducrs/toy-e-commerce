package ro.appptozee.ecommerce.common.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationEvent;


public class PaymentCompleted extends ApplicationEvent {
    private OrderDto orderDto;
    private PaymentStatus paymentStatus;

    public PaymentCompleted(Object source, OrderDto orderDto, PaymentStatus paymentStatus) {
        super(source);
        this.orderDto = orderDto;
        this.paymentStatus = paymentStatus;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public OrderDto getOrderDto() {
        return orderDto;
    }
}
