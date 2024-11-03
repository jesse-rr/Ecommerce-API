package org.ecommerce.product.dto.mapper;

import org.ecommerce.product.dto.ProductRequestDTO;
import org.ecommerce.product.model.Product;
import org.ecommerce.product.dto.ProductPurchaseResponse;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toProduct(ProductRequestDTO request) {
        return Product.builder()
                .stockQuantity(request.stockQuantity())
                .price(request.price())
                .name(request.name())
                .dimensions(request.dimentions())
                .description(request.description())
                .productImage(request.productImage())
                .status(request.status())
                .build();
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, int requestQuantity) {
        return new ProductPurchaseResponse(
                product.getProductId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                requestQuantity,
                product.getStatus()
        );
    }
}
