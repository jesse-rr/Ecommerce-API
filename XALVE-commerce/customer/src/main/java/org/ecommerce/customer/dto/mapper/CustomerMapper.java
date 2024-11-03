package org.ecommerce.customer.dto.mapper;

import org.ecommerce.customer.dto.CustomerRequestDTO;
import org.ecommerce.customer.dto.CustomerResponseDTO;
import org.ecommerce.customer.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public Customer toCustomer(CustomerRequestDTO dto) {
        return Customer.builder()
                .firstname(dto.firstname())
                .lastname(dto.lastname())
                .email(dto.email())
                .password(dto.password())
                .address(dto.address())
                .cpfCnpj(dto.cpfCnpj())
                .phone(dto.phone())
                .build();
    }

    public CustomerResponseDTO toCustomerResponseDTO(Customer customer) {
        return new CustomerResponseDTO(
                customer.getCustomerId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail()
        );
    }
}
