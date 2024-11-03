package com.ecommerce.discount.dto.mapper;

import com.ecommerce.discount.dto.DiscountDTO;
import com.ecommerce.discount.model.Discount;
import org.springframework.stereotype.Service;

@Service
public class DiscountMapper {

    public DiscountDTO toDiscountDTO(Discount discount) {
        return new DiscountDTO(
                discount.getProductId(),
                discount.getDiscountValue(),
                discount.getExpirationTime(),
                discount.isActive()
        );
    }

    public Discount toDiscount(DiscountDTO request) {
        return Discount.builder()
                .expirationTime(request.expirationDate())
                .discountValue(request.discountValue())
                .productId(request.productId())
                .isActive(request.isActive())
                .build();
    }
}
