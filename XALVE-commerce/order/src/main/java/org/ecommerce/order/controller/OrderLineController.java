package org.ecommerce.order.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ecommerce.order.dto.OrderLineDTO;
import org.ecommerce.order.service.OrderLineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order-lines")
public class OrderLineController {

    private final OrderLineService service;

    @PostMapping
    public ResponseEntity<Long> addOrderLine(
            @RequestBody @Valid OrderLineDTO request
    ) {
        return ResponseEntity.ok(service.addOrderLine(request));
    }
}
