package ro.appptozee.ecommerce.orders.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.appptozee.ecommerce.orders.domain.OrderService;
import ro.appptozee.ecommerce.users.entities.User;
import ro.appptozee.ecommerce.users.services.PrincipalUserDetails;

@RestController
public class OrdersController {

    @Autowired
    private OrderService orderService;

    @PostMapping("api/addtocart/product/{pId}")
    public ResponseEntity<CartAction> addToCart(@PathVariable("pId") long productId){
        // retrieve authenticated user
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = ((PrincipalUserDetails) authentication.getPrincipal()).user();
        var added = orderService.addToCart(loggedInUser.getId(), productId);
        return ResponseEntity.accepted().body(new CartAction(added.succeded(), added.cartId()));
    }

    @PostMapping("api/checkout/{cId}")
    public ResponseEntity<CheckoutAction> checkout(@PathVariable("cId") long cartId){
        // retrieve authenticated user
        boolean added = orderService.checkout(cartId);
        return ResponseEntity.accepted().body(new CheckoutAction(added));
    }
}
