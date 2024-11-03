package org.ecommerce.order.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ecommerce.order.dto.OrderRequestDTO;
import org.ecommerce.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<Long> addOrder(
            @RequestBody @Valid OrderRequestDTO request
    ) {
        return ResponseEntity.ok(service.addOrder(request));
    }
}
