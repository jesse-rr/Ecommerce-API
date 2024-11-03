package com.ecommerce.discount.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Discount {

    @Id
    private String discountId;

    private Long productId;
    private double discountValue;
    private LocalDateTime expirationTime;
    private boolean isActive;
}
