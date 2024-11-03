package org.ecommerce.product.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Entity(name = "product")
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String name;
    private String description;
    private BigDecimal price;
    @ElementCollection
    private List<Double> dimensions;
    private int stockQuantity;
    private String productImage;
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

}
