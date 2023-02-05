package com.neurogine.assignment.demo.service;

import com.neurogine.assignment.demo.entity.Product;
import com.neurogine.assignment.demo.repository.ProductRepository;
import com.neurogine.assignment.demo.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    /**
     *
     * @return List of all products
     */
    public List<Product> listAll(){
        return (List<Product>) productRepository.findAll();
    }

    /**
     *
     * @param product - new product to save
     */
    public void save(Product product) {
        productRepository.save(product);
    }

    /**
     *
     * @param id - id of a product
     * @return - saved product with given id
     * @throws ProductNotFoundException
     */
    public Product get(Long id) throws ProductNotFoundException{
        Optional<Product> result = productRepository.findById(id);
        if (result.isPresent()){
            return result.get();
        }else{
            throw new ProductNotFoundException("Could Not Find a Product With Given ID");
        }
    }

    /**
     *
     * @param id - id of a product to delete
     * @throws ProductNotFoundException
     */
    public void delete(Long id)  throws ProductNotFoundException{
        Long count=productRepository.countById(id);
        if(count ==null || count==0){
            throw new ProductNotFoundException("Could Not Find a Product With Given ID");
        }
        productRepository.deleteById(id);
    }
}
