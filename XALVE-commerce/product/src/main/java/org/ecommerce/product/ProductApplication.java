package org.ecommerce.product;

import org.ecommerce.product.model.Product;
import org.ecommerce.product.model.ProductStatus;
import org.ecommerce.product.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadProducts(ProductRepository repository) {
        return args -> {
            Product product1 = new Product();
            product1.setName("Example Product 1");
            product1.setDescription("This is an example product description 1.");
            product1.setStockQuantity(100);
            product1.setPrice(new BigDecimal("29.99"));
            product1.setDimensions(List.of(10.0, 5.5, 3.0));
            product1.setProductImage("http://example.com/image1.jpg");
            product1.setStatus(ProductStatus.ACTIVE);

            Product product2 = new Product();
            product2.setName("Example Product 2");
            product2.setDescription("This is an example product description 2.");
            product2.setStockQuantity(200);
            product2.setPrice(new BigDecimal("39.99"));
            product2.setDimensions(List.of(12.0, 6.0, 4.0));
            product2.setProductImage("http://example.com/image2.jpg");
            product2.setStatus(ProductStatus.ACTIVE);

            Product product3 = new Product();
            product3.setName("Example Product 3");
            product3.setDescription("This is an example product description 3.");
            product3.setStockQuantity(150);
            product3.setPrice(new BigDecimal("19.99"));
            product3.setDimensions(List.of(8.0, 4.0, 2.5));
            product3.setProductImage("http://example.com/image3.jpg");
            product3.setStatus(ProductStatus.ACTIVE);

            Product product4 = new Product();
            product4.setName("Example Product 4");
            product4.setDescription("This is an example product description 4.");
            product4.setStockQuantity(250);
            product4.setPrice(new BigDecimal("49.99"));
            product4.setDimensions(List.of(15.0, 7.0, 5.0));
            product4.setProductImage("http://example.com/image4.jpg");
            product4.setStatus(ProductStatus.ACTIVE);

            Product product5 = new Product();
            product5.setName("Example Product 5");
            product5.setDescription("This is an example product description 5.");
            product5.setStockQuantity(300);
            product5.setPrice(new BigDecimal("59.99"));
            product5.setDimensions(List.of(20.0, 10.0, 6.0));
            product5.setProductImage("http://example.com/image5.jpg");
            product5.setStatus(ProductStatus.ACTIVE);

            repository.saveAll(List.of(product1, product2, product3, product4, product5));
        };
    }

}
