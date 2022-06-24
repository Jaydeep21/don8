package com.don8.controller;

import com.don8.model.Image;
import com.don8.model.Product;
import com.don8.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @PostMapping(value = "/product", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    private Product saveProduct(@RequestPart("product") Product product,
                                @RequestPart("imageFile") MultipartFile[] file)
    {
       // productService.saveOrUpdate(product);
        try{
            Set<Image> images = uploadImage(file);
            product.setImage(images);
           return productService.saveOrUpdate(product);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Set<Image> uploadImage(MultipartFile[] multipartFiles) throws IOException
    {
        Set<Image> images = new HashSet<>();

        for(MultipartFile file: multipartFiles){
            Image image = new Image(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            images.add(image);
        }
        return images;
    }


    //creating put mapping that updates the product detail
    @PutMapping("/product")
    private Product update(@RequestBody Product product)
    {
        productService.saveOrUpdate(product);
        return product;
    }
}
