package com.don8.controller;

import com.don8.model.Product;
import com.don8.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

public class ProductController {


    @Autowired
    ProductService productService;
    //creating a get mapping that retrieves all the product detail from the database
    @GetMapping("/product")
    private List<Product> getAllProducts()
    {
        return productService.getAllProducts();
    }
    //creating a get mapping that retrieves the detail of a specific product
    @GetMapping("/product/{pid}")
    private Product getProduct(@PathVariable("pid") int pid)
    {
        return productService.getProductById(BigInteger.valueOf(pid));
    }
    //creating a delete mapping that deletes a specified product
    @DeleteMapping("/product/{pid}")
    private void deleteProduct(@PathVariable("pid") int pid)
    {
        productService.delete(pid);
    }
    //creating post mapping that post the product detail in the database
    @PostMapping("/product")
    private Long saveBook(@RequestBody Product product)
    {
        productService.saveOrUpdate(product);
        return product.getPid();
    }
    //creating put mapping that updates the product detail
    @PutMapping("/product")
    private Product update(@RequestBody Product product)
    {
        productService.saveOrUpdate(product);
        return product;
    }
}
