package com.example.pickit.service;

import com.example.pickit.domain.Address;
import com.example.pickit.domain.User;
import com.example.pickit.repository.AddressRepository;
import com.example.pickit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Transactional
    public void saveAddress(Long userId, String city, String street, String zipcode) {
        User findUser = userRepository.findUser(userId);
        Address newAddress = Address.createAddress(findUser, city, street, zipcode);
        addressRepository.saveAddress(newAddress);
    }

    @Transactional
    public void deleteAddress(Long addressId) {
        addressRepository.updateAddressStatus(addressId);
    }

    @Transactional
    public void updateAddress(Long userId, Long addressId, Address address) {
        addressRepository.updateAddressDetails(userId, addressId, address);
    }

    @Transactional
    public void changeMainAddress(Long userId,Long addressId) {
        addressRepository.changeMainAddress(userId, addressId);
    }
}
