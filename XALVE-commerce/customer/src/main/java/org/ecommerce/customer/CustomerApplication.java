package org.ecommerce.customer;

import org.ecommerce.customer.model.Customer;
import org.ecommerce.customer.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner addCustomer(CustomerRepository repository) {
//		return args -> {
//			Customer customer1 = new Customer();
//			customer1.setFirstname("John");
//			customer1.setLastname("Doe");
//			customer1.setEmail("john.doe@example.com");
//			customer1.setPassword("securePassword123");
//			customer1.setAddress("123 Main St, Springfield, USA");
//			customer1.setCpfCnpj("123.456.789-00");
//			customer1.setPhone("(123) 456-7890");
//
//			Customer customer2 = new Customer();
//			customer2.setFirstname("Jane");
//			customer2.setLastname("Smith");
//			customer2.setEmail("jane.smith@example.com");
//			customer2.setPassword("password456");
//			customer2.setAddress("456 Elm St, Springfield, USA");
//			customer2.setCpfCnpj("987.654.321-00");
//			customer2.setPhone("(234) 567-8901");
//
//			Customer customer3 = new Customer();
//			customer3.setFirstname("Alice");
//			customer3.setLastname("Johnson");
//			customer3.setEmail("alice.johnson@example.com");
//			customer3.setPassword("alicePass789");
//			customer3.setAddress("789 Oak St, Springfield, USA");
//			customer3.setCpfCnpj("321.654.987-00");
//			customer3.setPhone("(345) 678-9012");
//
//			Customer customer4 = new Customer();
//			customer4.setFirstname("Bob");
//			customer4.setLastname("Williams");
//			customer4.setEmail("bob.williams@example.com");
//			customer4.setPassword("bobPass101");
//			customer4.setAddress("321 Pine St, Springfield, USA");
//			customer4.setCpfCnpj("654.321.098-00");
//			customer4.setPhone("(456) 789-0123");
//
//			Customer customer5 = new Customer();
//			customer5.setFirstname("Charlie");
//			customer5.setLastname("Brown");
//			customer5.setEmail("charlie.brown@example.com");
//			customer5.setPassword("charliePass202");
//			customer5.setAddress("159 Maple St, Springfield, USA");
//			customer5.setCpfCnpj("789.012.345-00");
//			customer5.setPhone("(567) 890-1234");
//
//			repository.saveAll(List.of(customer1,customer2,customer3,customer4,customer5));
//		};
//	}
}
