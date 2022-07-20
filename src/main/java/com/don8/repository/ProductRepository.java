package com.don8.repository;

import com.don8.model.dbentity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 @author BHARAT VAYITLA
 */

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByPid(Long pid);
}
