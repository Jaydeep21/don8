package com.don8.core.port.outbound;

import com.don8.core.model.dbentity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 @author BHARAT VAYITLA
 */

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByPid(Long pid);
    Page<Product> findByUid(Long id, Pageable pageable);
}
