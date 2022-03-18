package ro.appptozee.ecommerce.common.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

@Data
@Builder
public class PaymentCompleted extends ApplicationEvent {
    private OrderDto orderDto;
    private PaymentStatus paymentStatus;
}
