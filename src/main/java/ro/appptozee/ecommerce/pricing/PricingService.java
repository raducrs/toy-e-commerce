package ro.appptozee.ecommerce.pricing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.appptozee.ecommerce.common.dto.pricing.Item;
import ro.appptozee.ecommerce.common.dto.pricing.Items;
import ro.appptozee.ecommerce.pricing.entities.PricingProduct;
import ro.appptozee.ecommerce.pricing.entities.PricingProductRepository;

@Service
public class PricingService {
    @Autowired
    private PricingProductRepository repository;

    public int getPrice(Items items) {
        return items.items().stream()
                .mapToInt(this::priceForItem)
                .sum();
    }
    private int priceForItem(Item item){
        return repository.findById(item.productId())
                .map(PricingProduct::getPrice)
                .orElse(0);
    }
}
