package com.don8.repository;

import com.don8.model.Product;
import com.don8.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigInteger;
import java.util.Optional;

/**
 @author BHARAT VAYITLA
 */

public interface ProductRepository extends JpaRepository<Product, BigInteger> {
    Optional<Product> findByProductId(String pid);
}
