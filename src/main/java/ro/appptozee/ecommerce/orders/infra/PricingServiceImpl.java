package ro.appptozee.ecommerce.orders.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.appptozee.ecommerce.common.dto.pricing.Item;
import ro.appptozee.ecommerce.common.dto.pricing.Items;
import ro.appptozee.ecommerce.orders.domain.Order;
import ro.appptozee.ecommerce.orders.domain.services.PricingService;

import java.util.stream.Collectors;

@Component
public class PricingServiceImpl implements PricingService {
    @Autowired
    private ro.appptozee.ecommerce.pricing.PricingService pricingService;

    @Override
    public int computePrice(Order order) {
        return pricingService.getPrice(new Items(
                order.getItems().keySet().stream()
                        .map(Item::new)
                        .collect(Collectors.toList())
        ));
    }
}
