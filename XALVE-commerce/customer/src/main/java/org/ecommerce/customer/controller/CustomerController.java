package org.ecommerce.customer.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ecommerce.customer.dto.CustomerRequestDTO;
import org.ecommerce.customer.dto.CustomerResponseDTO;
import org.ecommerce.customer.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    public ResponseEntity<String> addCustomer(
            @RequestBody @Valid CustomerRequestDTO request
    ) {
        return ResponseEntity.ok(service.addCustomer(request));
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponseDTO> findCustomerById(
            @PathVariable("customer-id") String customerId
    ) {
        return ResponseEntity.ok(service.findCustomerById(customerId));
    }

    @GetMapping("/document/{cpfCnpj}")
    public ResponseEntity<CustomerResponseDTO> findCustomerByCpfCnpj(
            @PathVariable("cpfCnpj") String cpfCnpj
    ) {
        return ResponseEntity.ok(service.findCustomerByCpfCnpj(cpfCnpj));
    }
}
