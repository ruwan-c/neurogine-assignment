package com.neurogine.assignment.demo.repository;

import com.neurogine.assignment.demo.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Long countById(Long id);

}
