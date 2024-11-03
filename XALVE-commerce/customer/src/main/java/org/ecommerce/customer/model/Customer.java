package org.ecommerce.customer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    @Id
    private String customerId;

    private String firstname;
    private String lastname;
    @Indexed(unique = true)
    private String email;
    private String password;
    @Indexed(unique = true)
    private String cpfCnpj;
    private String phone;
    private String address;

}
