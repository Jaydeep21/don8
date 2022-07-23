package com.don8.core.service;

import com.don8.config.JwtUtils;
import com.don8.core.decorators.TokenUtil;
import com.don8.core.model.dbentity.Product;
import com.don8.core.model.dbentity.User;
import com.don8.core.model.exception.ResourceNotFoundException;
import com.don8.core.port.inbound.IUserService;
import com.don8.core.port.outbound.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 @author BHARAT VAYITLA
 */


@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Value("${prod.url}")
    String url;

    @Autowired
    IUserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    TokenUtil tokenUtil;

    //getting all product record by using the method findaAll() of CrudRepository
    public Page<Product> getAllProducts(Pageable page)
    {
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

    public Page<Product> getProductsByUserId(HttpServletRequest request, Pageable pageable) {
        return productRepository.findByUid(tokenUtil.getUserFromToken(request).getUid(), pageable);
    }
}
