package org.ecommerce.order.openFeign.payment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "payment-service",
        url = "${application.feign.payment-url}"
)
public interface PaymentClient {

    @PostMapping
    Long addPayment(@RequestBody PaymentRequestDTO request);
}
