package org.ecommerce.order.openFeign.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "product-service",
        url = "${application.feign.product-url}"
)
public interface ProductClient {

    @PostMapping("/purchase")
    List<ProductPurchaseResponse> purchaseProducts(@RequestBody List<ProductPurchaseRequest> request);
}
