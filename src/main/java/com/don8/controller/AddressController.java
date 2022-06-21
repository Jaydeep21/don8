package com.don8.controller;

import com.don8.model.dbentity.Address;
import com.don8.model.response.GenericResponse;
import com.don8.port.inbound.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class AddressController {

    @Autowired
    private IAddressService addressService;

    @PostMapping("/{userId}/address")
    public ResponseEntity<?> storeAddress(@PathVariable (value = "userId") Long userId,@RequestBody Address address) {
        addressService.storeAddress(userId, address);
        return new ResponseEntity(GenericResponse.builder().message("Success").body("Address Stored Successfully").build(), HttpStatus.OK);
    }
    @DeleteMapping("/{userId}/address/{addressId}")
    public ResponseEntity<?> deleteAddressById(@PathVariable (value = "userId") Long userId,
    @PathVariable (value = "addressId") Long addressId) {
        addressService.deleteAddressById(userId, addressId);
        return ResponseEntity.ok(GenericResponse.builder().message("Success").body("Address Deleted Successfully").build());
    }
    @PutMapping("/{userId}/address/{addressId}")
    public ResponseEntity<?> updateAddressById(@PathVariable (value = "userId") Long userId,
                                               @PathVariable (value = "addressId") Long addressId,@RequestBody Address ad) throws Exception {
        return ResponseEntity.ok(GenericResponse.builder().message("Success").body(addressService.updateAddressById(userId,addressId, ad)).build());
    }
    @GetMapping("/{userId}/address")
    public ResponseEntity<?> getAddressByUserId(@PathVariable(name = "userId") Long userId, Pageable pageable){
        return ResponseEntity.ok(GenericResponse.builder().message("Success").body(addressService.getAddressByUserId(userId, pageable)).build());
    }
    @GetMapping("/{userId}/address/{addressId}")
    public ResponseEntity<?> getAddressById(@PathVariable (value = "userId") Long userId,
                                            @PathVariable (value = "addressId") Long addressId){
        return ResponseEntity.ok(GenericResponse.builder().message("Success").body(addressService.getAddressById(userId, addressId)).build());
    }

}