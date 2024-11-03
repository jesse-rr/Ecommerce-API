package com.ecommerce.discount;

import com.ecommerce.discount.model.Discount;
import com.ecommerce.discount.repository.DiscountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class DiscountApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscountApplication.class, args);
    }


//    @Bean
//    public CommandLineRunner commandLineRunner(DiscountRepository repository) {
//        return args -> {
//            Discount discount1 = new Discount();
//            discount1.setProductId(1L);
//            discount1.setExpirationTime(LocalDateTime.now().plusHours(1));
//            discount1.setDiscountValue(0.15);
//            discount1.setActive(true);
//
//            Discount discount2 = new Discount();
//            discount2.setProductId(2L);
//            discount2.setExpirationTime(LocalDateTime.now().plusHours(2));
//            discount2.setDiscountValue(0.20);
//            discount2.setActive(true);
//
//            Discount discount3 = new Discount();
//            discount3.setProductId(3L);
//            discount3.setExpirationTime(LocalDateTime.now().plusHours(3));
//            discount3.setDiscountValue(0.10);
//            discount3.setActive(true);
//
//            Discount discount4 = new Discount();
//            discount4.setProductId(4L);
//            discount4.setExpirationTime(LocalDateTime.now().plusHours(4));
//            discount4.setDiscountValue(0.25);
//            discount4.setActive(true);
//
//            Discount discount5 = new Discount();
//            discount5.setProductId(5L);
//            discount5.setExpirationTime(LocalDateTime.now().plusHours(5));
//            discount5.setDiscountValue(0.30);
//            discount5.setActive(true);
//
//            repository.saveAll(List.of(discount1, discount2, discount3, discount4, discount5));
//        };
//    }
}
