package org.ecommerce.cart.controller;

import lombok.RequiredArgsConstructor;
import org.ecommerce.cart.model.Items;
import org.ecommerce.cart.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {

    private final CartService service;

    @PostMapping("/add/{customer_id}")
    public ResponseEntity<Void> addOrUpdateProduct(
            @PathVariable("customer_id") String customerId,
            @RequestBody Items request
    ) {
        service.addOrUpdateProduct(customerId, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{customer_id}")
    public ResponseEntity<List<Items>> getCart(
            @PathVariable("customer_id") String customerId
    ) {
        return ResponseEntity.ok(service.getCart(customerId));
    }

    @DeleteMapping("/{customer_id}/clear")
    public ResponseEntity<Void> clearCart(
            @PathVariable("customer_id") String customerId
    ) {
        service.clearCart(customerId);
        return ResponseEntity.ok().build();
    }
}
