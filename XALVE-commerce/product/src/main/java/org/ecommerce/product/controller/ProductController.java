package org.ecommerce.product.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ecommerce.product.dto.ProductRequestDTO;
import org.ecommerce.product.dto.ProductPurchaseRequest;
import org.ecommerce.product.dto.ProductPurchaseResponse;
import org.ecommerce.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<Long> addProduct(
            @RequestBody @Valid ProductRequestDTO request
    ) {
        return ResponseEntity.ok(service.addProduct(request));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(
            @RequestBody @Valid List<ProductPurchaseRequest> requestedProducts
    ) {
        return ResponseEntity.ok(service.purchaseProducts(requestedProducts));
    }
}
