package com.neurogine.assignment.demo;

import com.neurogine.assignment.demo.entity.Product;
import com.neurogine.assignment.demo.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.util.Optional;

@DataJpaTest
//@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductRepositoryTests {
    @Autowired
    private ProductRepository productRepository;

    @Test
    @Order(1)
    public void testAddNew(){
        Product product=new Product();
//        product.setId(1L); auto increment start with 1
        product.setDescription("Test Laptop");
        product.setPrice(new BigDecimal(700.00));

        Product savedProduct = productRepository.save(product);
        Assertions.assertThat(savedProduct).isNotNull();
        Assertions.assertThat(savedProduct.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void testListAll(){
        Iterable<Product> products = productRepository.findAll();
        Assertions.assertThat(products).hasSizeGreaterThan(0);

    }
    @Test
    @Order(3)
    public void testUpdate(){
        Optional<Product> optionalProductObj = productRepository.findById(1L);
        optionalProductObj.get().setDescription("Test Laptop Updated");
        Product updatedObj=productRepository.save(optionalProductObj.get());

        Assertions.assertThat(updatedObj.getDescription()).isEqualTo("Test Laptop Updated");

    }
    @Test
    @Order(4)
    public void testRead(){
        Optional<Product> optionalProductObj = productRepository.findById(1L);
        Assertions.assertThat(optionalProductObj.isPresent());
        System.out.println(optionalProductObj.get());

    }
    @Test
    @Order(5)
    public void testDelete(){
        productRepository.deleteById(1L);

        Optional<Product> optionlProduct = productRepository.findById(1L);
        Assertions.assertThat(optionlProduct).isNotPresent();

    }
}
