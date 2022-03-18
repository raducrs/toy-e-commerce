package ro.appptozee.ecommerce.inventory.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.appptozee.ecommerce.common.dto.InventoryStatus;
import ro.appptozee.ecommerce.common.dto.OrderDto;
import ro.appptozee.ecommerce.common.dto.ReservationStatus;
import ro.appptozee.ecommerce.inventory.service.InventoryService;
import ro.appptozee.ecommerce.inventory.service.ItemUnavailable;

@RestController
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("api/reserve")
    public ResponseEntity<InventoryStatus> reserve(@RequestBody OrderDto orderDto){
        try{
            inventoryService.reserve(orderDto);
            return ResponseEntity.ok(new InventoryStatus(ReservationStatus.RESERVED));
        } catch (ItemUnavailable itemUnavailable){
            return new ResponseEntity<>(new InventoryStatus(ReservationStatus.UNDERSTOCKED), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("api/checkin")
    public ResponseEntity<InventoryStatus> checkin(@RequestBody OrderDto orderDto){
        try{
            inventoryService.checkin(orderDto);
            return ResponseEntity.ok(new InventoryStatus(ReservationStatus.RESERVED));
        } catch (ItemUnavailable itemUnavailable){
            return new ResponseEntity<>(new InventoryStatus(ReservationStatus.UNDERSTOCKED), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("api/products/{productId}/available/{quantity}")
    public ResponseEntity<InventoryStatus> reserve(@PathVariable("productId") long productId,
                                                   @PathVariable("quantity") int quantity){
            boolean inStock = inventoryService.isInStock(productId,quantity);
            return ResponseEntity.ok(new InventoryStatus(
                    inStock ? ReservationStatus.IN_STOCK: ReservationStatus.UNDERSTOCKED));

        }
}

