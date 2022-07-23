package com.don8.core.port.outbound;

import com.don8.core.model.dbentity.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Page<Address> findByUserUid(Long userId, Pageable pageable);
    Optional<Address> findByAidAndUserUid(Long addressId, Long userId);
}
