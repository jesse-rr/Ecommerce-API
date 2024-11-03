package org.ecommerce.customer.service;

import lombok.RequiredArgsConstructor;
import org.ecommerce.customer.dto.CustomerRequestDTO;
import org.ecommerce.customer.dto.CustomerResponseDTO;
import org.ecommerce.customer.dto.mapper.CustomerMapper;
import org.ecommerce.customer.exception.CustomerNotFoundException;
import org.ecommerce.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public String addCustomer(CustomerRequestDTO request) {
        return repository.save(mapper.toCustomer(request)).getCustomerId();
    }

    public CustomerResponseDTO findCustomerById(String customerId) {
        return repository.findById(customerId)
                .map(mapper::toCustomerResponseDTO)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("CUSTOMER NOT FOUND ID :: <%s>",customerId)));
    }

    public CustomerResponseDTO findCustomerByCpfCnpj(String cpfCnpj) {
        return repository.findCustomerByCpfCnpj(cpfCnpj)
                .map(mapper::toCustomerResponseDTO)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("CUSTOMER NOT FOUND CPF-CNPJ :: <%s>",cpfCnpj)));
    }
}
