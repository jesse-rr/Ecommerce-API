package org.ecommerce.customer.repository;

import org.ecommerce.customer.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    Optional<Customer> findCustomerByCpfCnpj(String cpfCnpj);
}
