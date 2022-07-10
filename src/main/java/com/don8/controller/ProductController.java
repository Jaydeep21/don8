package com.don8.controller;

import com.don8.model.dbentity.Product;
import com.don8.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 @author BHARAT VAYITLA
 */

@RestController
public class ProductController {

    @Autowired
    private ObjectMapper mapper;
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
        return productService.getProductById(Long.valueOf(pid));
    }
    //creating a delete mapping that deletes a specified product
    @DeleteMapping("/product/{pid}")
    private void deleteProduct(@PathVariable("pid") int pid)
    {
        productService.delete(pid);
    }

    //creating post mapping that post the product detail in the database
    @PostMapping(value = "/{productId}", consumes ={ MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Product saveProduct( @PathVariable Long productId,
                               @Valid @RequestPart("product") String product,
                                @RequestParam(value="productImage", required=false) MultipartFile productImageFile)
    {
       // productService.saveOrUpdate(product);
        Product modelDTO = null;
        try{
           modelDTO = mapper.readValue(product, Product.class);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
        return productService.saveOrUpdate(productId, modelDTO, productImageFile);
    }
}
