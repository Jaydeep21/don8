package com.don8.adapter.inbound.controller;

import com.don8.adapter.inbound.controller.UserController;
import com.don8.config.TestSecurityConfig;
import com.don8.core.model.dbentity.Product;
import com.don8.core.model.dbentity.User;
import com.don8.core.port.inbound.IUserService;
import com.don8.core.service.ProductService;

import org.junit.Before;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import org.junit.platform.runner.JUnitPlatform;
@SpringBootTest()
//classes = TestSecurityConfig.class
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class})
//@TestPropertySource(locations="classpath:test.properties")
class ProductControllerTest {
    @InjectMocks
    ProductController productController;

    @Mock
    ProductService productService;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getProductByIdTest() {
 
        when(productService.getProductById((long)1)).thenReturn(
            Product.builder()
            .uid((long)31)
            .productName("Water").build()
        );

        Product product = productController.getProduct(31);
        assertThat(product.getProductName()).isEqualTo("Water");

    }

    @Test
    void updateProductByIdTest() {
 
        Product updated = new Product().builder().pid((long)31).productName("Water Updated").build();

        MultipartFile productImageFile = new MultipartFile() {

            @Override
            public String getName() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public String getOriginalFilename() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public String getContentType() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public boolean isEmpty() {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public long getSize() {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public InputStream getInputStream() throws IOException {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {
                // TODO Auto-generated method stub
                
            }
            
        };
        when(productService.update((long)31, updated, productImageFile)).thenReturn(
           updated
        );

        Product product = productController.updateProduct((long)31, "Water updated", productImageFile);
        assertThat(product.getProductName()).isEqualTo("Water updated");

    }
}