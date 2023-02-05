package com.neurogine.assignment.demo;

import com.neurogine.assignment.demo.entity.Product;
import com.neurogine.assignment.demo.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);


    }

    @Bean
    public CommandLineRunner demoData(ProductRepository productRepository) {
        return args -> {
            Product p1 = new Product();
            p1.setDescription("Lenovo 2022 Newest Ideapad 3");
            p1.setPrice(new BigDecimal("289.00"));

            Product p2 = new Product();
            p2.setDescription("2021 HP Stream 14 HD SVA Laptop");
            p2.setPrice(new BigDecimal("381.00"));

            Product p3 = new Product();
            p3.setDescription("LSAMSUNG 2022 14” FHD(1920 x 1080) Laptop");
            p3.setPrice(new BigDecimal("174.00"));

            Product p4 = new Product();
            p4.setDescription("2022 ASUS 14 Thin Light");
            p4.setPrice(new BigDecimal("229.00"));

            productRepository.save(p1);
            productRepository.save(p2);
            productRepository.save(p3);
            productRepository.save(p4);
        };
    }

}
