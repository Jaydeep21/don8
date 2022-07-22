package com.don8.controller;

import com.don8.model.dbentity.Product;
import com.don8.model.response.GenericResponse;
import com.don8.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 @author BHARAT VAYITLA
 */

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ObjectMapper mapper;
    @Autowired
    ProductService productService;

    //creating a get mapping that retrieves all the product detail from the database
    @GetMapping("")
    private Page<Product> getAllProducts(Pageable pageable)
    {
        return productService.getAllProducts(pageable);
    }

    //creating a get mapping that retrieves the detail of a specific product
    @GetMapping("/{pid}")
    private Product getProduct(@PathVariable("pid") int pid)
    {
        return productService.getProductById(Long.valueOf(pid));
    }
    //creating a delete mapping that deletes a specified product
    @DeleteMapping("/{pid}")
    private void deleteProduct(@PathVariable("pid") int pid)
    {
        productService.delete(pid);
    }

    //creating post mapping that post the product detail in the database
    @PutMapping(value = "/{productId}", consumes ={ MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Product updateProduct( @PathVariable Long productId,
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
        return productService.update(productId, modelDTO, productImageFile);
    }

    @PostMapping(value = "/", consumes ={ MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> saveProduct(
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
        return ResponseEntity.ok(productService.save( modelDTO, productImageFile));
    }

    @GetMapping(value = "/image/{productId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource getProfile(@PathVariable Long productId){
        return new ByteArrayResource(productService.getImage(productId));
    }

    @GetMapping("/byUser")
    public Page<Product> getProductsByUser(HttpServletRequest request, Pageable pageable)
    {
        return productService.getProductsByUserId(request,pageable);
    }
}
