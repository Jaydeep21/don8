package com.don8.port.inbound;

import com.don8.model.dbentity.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAddressService {
    public void storeAddress(Long userId, Address address);
    public Boolean deleteAddressById(Long userId, Long addressId);
    public Address updateAddressById( Long userId, Long addressId, Address ad);
    public Page<Address> getAddressByUserId(Long id, Pageable pageable);
    public Address getAddressById(Long userId, Long addressId);
}
