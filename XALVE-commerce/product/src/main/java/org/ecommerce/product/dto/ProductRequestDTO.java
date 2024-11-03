package org.ecommerce.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.ecommerce.product.model.ProductStatus;

import java.math.BigDecimal;
import java.util.List;

public record ProductRequestDTO(
        @NotNull @NotBlank
        String name,
        @NotNull @NotBlank
        String description,
        @NotNull @Positive
        BigDecimal price,
        @NotNull
        List<Double> dimentions,
        @NotNull @Positive
        int stockQuantity,
        @NotNull @NotBlank
        String productImage,
        @NotNull
        ProductStatus status
) {
}
