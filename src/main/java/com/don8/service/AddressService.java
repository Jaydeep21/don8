package com.don8.service;

import com.don8.model.dbentity.Address;
import com.don8.model.exception.ResourceNotFoundException;
import com.don8.port.inbound.IAddressService;
import com.don8.repository.AddressRepository;
import com.don8.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AddressService implements IAddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Page<Address> getAddressByUserId(Long id, Pageable pageable){
        return addressRepository.findByUserUid(id, pageable);
    }

    @Override
    public Address getAddressById(Long userId, Long addressId){
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("UserId " + userId + " not found");
        }
        return addressRepository.findByAidAndUserUid(addressId,userId).orElseThrow(() -> new ResourceNotFoundException("Address id "+ addressId + " not found"));
    }
    @Override
    public Address updateAddressById( Long userId, Long addressId, Address ad){
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("UserId " + userId + " not found");
        }
        return addressRepository.findById(addressId).map(address -> {
            address.setCity(ad.getCity());
            address.setCountry(ad.getCountry());
            address.setLine1(ad.getLine1());
            address.setLine2(ad.getLine2());
            address.setGcode(ad.getGcode());
            address.setLongitudeLatitude(ad.getLongitudeLatitude());
            address.setState(ad.getState());
            address.setPinCode(ad.getPinCode());
            address.setName(ad.getName());
            address.setPhoneNumber(ad.getPhoneNumber());
            return addressRepository.save(address);
        }).orElseThrow(() -> new ResourceNotFoundException("AddressId " + addressId + "not found"));

    }
    @Override
    public Boolean deleteAddressById(Long userId, Long addressId){
        return addressRepository.findByAidAndUserUid(addressId,userId).map(comment -> {
            addressRepository.delete(comment);
            return false;
        }).orElseThrow(() -> new ResourceNotFoundException("User id " + userId + " with Address id "+ addressId + " not found"));
    }
    @Override
    public void storeAddress(Long userId, Address address){
        userRepository.findById(userId).map(user -> {
            address.setUser(user);
            return addressRepository.save(address);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }
}
