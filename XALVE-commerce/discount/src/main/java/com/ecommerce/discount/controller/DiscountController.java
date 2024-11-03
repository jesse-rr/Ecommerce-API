package com.ecommerce.discount.controller;

import com.ecommerce.discount.dto.DiscountDTO;
import com.ecommerce.discount.service.DiscountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/discounts")
public class DiscountController {

    private final DiscountService service;

    @PostMapping
    public ResponseEntity<String> createDiscount(
            @RequestBody @Valid DiscountDTO request
    ) {
        return ResponseEntity.ok(service.createDiscount(request));
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<DiscountDTO> getDiscountByProductId(
            @PathVariable("product-id") Long productId
    ) {
        return ResponseEntity.ok(service.getDiscountByProductId(productId));
    }
}
