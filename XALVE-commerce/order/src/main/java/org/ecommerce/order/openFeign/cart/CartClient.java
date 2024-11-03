package org.ecommerce.order.openFeign.cart;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "cart-service",
        url = "${application.feign.cart-url}"
)
public interface CartClient {

    @GetMapping("/{customer_id}")
    List<Items> getCart(@PathVariable("customer_id") String customerId);
}
