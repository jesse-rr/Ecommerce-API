package org.ecommerce.order.openFeign.discount;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "discount-service",
        url = "${application.feign.discount-url}"
)
public interface DiscountClient {

    @GetMapping("/{product-id}")
    DiscountDTO getDiscountByProductId(@PathVariable("product-id") Long productId);

}
