package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.domain.Response;
import com.demo.domain.user.Address;
import com.demo.domain.user.User;
import com.demo.exception.AddressNotFoundException;
import com.demo.exception.UserNotFoundException;
import com.demo.repositories.AddressRepository;
import com.demo.repositories.UserRepository;
import com.demo.util.ResponseUtil;

@Service
public class AddressService {
    
    private final AddressRepository repository;

    private final ResponseUtil util;

    private final UserRepository userRepo;

    @Autowired
    public AddressService(AddressRepository repository, ResponseUtil util, UserRepository userRepo){
        this.repository = repository;
        this.util = util;
        this.userRepo = userRepo;
    }

    public ResponseEntity<Response> createAddress(Address address, Long userId) throws UserNotFoundException{
        User user = this.userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException());
        Address newAddress = new Address();
        newAddress.setAddress(address.getAddress());
        newAddress.setFullName(address.getFullName());
        newAddress.setIsMainAddress(address.getIsMainAddress());
        newAddress.setPhoneNumber(address.getPhoneNumber());
        newAddress.setUser(user);
        this.repository.save(newAddress);
        return this.util.sendCreated("sukses membuat alamat", true, newAddress);
    }

    public ResponseEntity<Response> getAddress(Long userId) throws UserNotFoundException{
        User user = this.userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException());
        List<Address> address = user.getAddress();
        return this.util.sendOk("sukses mendapatkan data address", true, address);
    }

    public ResponseEntity<Response> getOneAddress(Long userId, Long addressId) throws UserNotFoundException, AddressNotFoundException{
        this.userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException());
        Address address = this.repository.getOneAddress(userId, addressId).orElseThrow(() -> new AddressNotFoundException());
        return this.util.sendOk("sukses mendapatkan data address", true, address);
    }

    public ResponseEntity<Response> deleteAddress(Long userId, Long addressId) throws UserNotFoundException, AddressNotFoundException{
        this.userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException());
        this.repository.deleteAddress(addressId, userId);
        return this.util.sendOk("sukses menghapus data address", true, null);
    }
}
