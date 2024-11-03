package org.ecommerce.order.openFeign.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "customer-service",
        url = "${application.feign.customer-url}"
)
public interface CustomerClient {

    @GetMapping("/document/{cpfCnpj}")
    Optional<CustomerResponseDTO> findCustomerByCpfCnpj(@PathVariable("cpfCnpj") String cpfCnpj);
}
