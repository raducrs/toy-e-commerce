package ro.appptozee.ecommerce.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ro.appptozee.ecommerce.common.dto.OrderDto;
import ro.appptozee.ecommerce.inventory.entities.InventoryProduct;
import ro.appptozee.ecommerce.inventory.entities.InventoryProductRepository;


@Service
public class InventoryService {

    @Autowired
    private InventoryProductRepository repository;

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = ItemUnavailable.class)
    public void reserve(OrderDto orderDto) throws ItemUnavailable{
        for(var lineItem : orderDto.cart()){
            // fail fast if stock is less
            if(!isInStock(lineItem.productId(),lineItem.quantity())){
                throw new ItemUnavailable();
            }
            // reserve stock
            repository.reserve(lineItem.productId(), lineItem.quantity());
            // rollback if understocked
            InventoryProduct inventoryProduct = repository.findById(lineItem.productId())
                    .orElseThrow(ItemUnavailable::new);
            if(inventoryProduct.getQuantity()<0){
                throw new ItemUnavailable();
            }
        }
    }

    @Transactional
    public void checkin(OrderDto orderDto){
        for(var lineItem : orderDto.cart()){
            // checkin stock
            repository.checkin(lineItem.productId(), lineItem.quantity());
        }
    }

    public boolean isInStock(long productId, int quantity) {
        InventoryProduct inventoryProduct = repository.findById(productId)
                .orElseThrow(ItemUnavailable::new);
        return (inventoryProduct.getQuantity()>=quantity);
    }
}
