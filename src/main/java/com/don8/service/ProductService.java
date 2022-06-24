package com.don8.service;

import com.don8.model.Product;
import com.don8.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    //getting all product record by using the method findaAll() of CrudRepository
    public List<Product> getAllProducts()
    {
        List<Product> product = new ArrayList<>();
        product.addAll(productRepository.findAll());
        return product;
    }
    //getting a specific record by using the method findById() of CrudRepository
    public Product getProductById(BigInteger pid)
    {
        return productRepository.findByPid(pid).orElse(null);
    }
    //saving a specific record by using the method save() of CrudRepository
    public Product saveOrUpdate(Product product)
    {
        productRepository.save(product);
        return product;
    }
    //deleting a specific record by using the method deleteById() of CrudRepository
    public void delete(int pid)
    {
        productRepository.deleteById(BigInteger.valueOf(pid));
    }
    //updating a record
    public void update(Product product, int pid)
    {
        productRepository.save(product);
    }
}
