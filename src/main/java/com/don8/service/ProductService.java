package com.don8.service;

import com.don8.model.dbentity.Product;
import com.don8.model.dbentity.User;
import com.don8.model.exception.ResourceNotFoundException;
import com.don8.repository.ProductRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 @author BHARAT VAYITLA
 */


@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Value("${prod.url}")
    String url;
    //getting all product record by using the method findaAll() of CrudRepository
    public Page<Product> getAllProducts(Pageable page)
    {
//        List<Product> product = new ArrayList<>();
//        product.addAll(productRepository.findAll());
        return productRepository.findAll(page);
    }
    //getting a specific record by using the method findById() of CrudRepository
    public Product getProductById(Long pid)
    {
        return productRepository.findByPid(pid).orElse(null);
    }


    //saving a specific record by using the method save() of CrudRepository
    public Product update(Long productId, Product p, MultipartFile product_image)
    {
        return productRepository.findById(productId).map(product -> {
            if(product_image!= null){
                product.setProduct_image_type(product_image.getContentType());
                product.setP_image_url(url+"product/image/"+String.valueOf(productId));
                try {
                    product.setProduct_image(product_image.getBytes());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }else{
                product.setProduct_image_type(null);
                product.setProduct_image(null);
                product.setP_image_url(null);
            }
            product.setUid(product.getUid());
            product.setProductName(p.getProductName());
            product.setDateAdded(p.getDateAdded());
            product.setDateExpiry(p.getDateExpiry());
            product.setDescription(p.getDescription());
            product.setAid(p.getAid());
            product.setPrice(p.getPrice());
            product.setIs_donated(p.getIs_donated());
            return productRepository.save(product);
        }).orElseThrow(() -> new ResourceNotFoundException("ProductId " + productId + " not found"));
    }
    //deleting a specific record by using the method deleteById() of CrudRepository
    public void delete(int pid)
    {
        productRepository.deleteById(Long.valueOf(pid));
    }
    //updating a record
    public Product save(Product product, MultipartFile product_image){
        try {
            product.setProduct_image(product_image.getBytes());
           // product.setP_image_url(url+"product/image"+String.valueOf(product.getPid()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        product.setProduct_image_type(product_image.getContentType());
        Product p=productRepository.save(product);
        p.setP_image_url(url+"product/image/"+String.valueOf(p.getPid()));
        return productRepository.save(p);
    }

    public byte[] getImage(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("ProductId " + productId + " not found"));
        if(product.getProduct_image()==null)
            throw new ResourceNotFoundException("ProductId " + productId + " does not have a image");
        return product.getProduct_image();
    }
}
