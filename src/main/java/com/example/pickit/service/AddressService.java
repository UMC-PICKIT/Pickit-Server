package com.example.pickit.service;

import com.example.pickit.domain.Address;
import com.example.pickit.domain.User;
import com.example.pickit.repository.AddressRepository;
import com.example.pickit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AddressService {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Transactional
    public void saveAddress(Long userId, String city, String street, String zipcode) {
        Optional<User> foundUser = userRepository.findUserById(userId);
        if (foundUser.isPresent()) {
            Address newAddress = Address.createAddress(foundUser.get(), city, street, zipcode);
            addressRepository.save(newAddress);
        } else {
            throw new IllegalStateException("유저를 찾을 수 없습니다");
        }
    }

    @Transactional
    public void deleteAddress(Long addressId) {
        Optional<Address> foundAddress = addressRepository.findById(addressId);
        if (foundAddress.isPresent()) {
            if (!foundAddress.get().getAddressStatus().equals("INACTIVE")) {
                foundAddress.get().setAddressStatus("INACTIVE");
                addressRepository.save(foundAddress.get());
            } else {
                throw new IllegalStateException("이미 삭제된 주소입니다");
            }
        } else {
            throw new IllegalStateException("주소를 찾을 수 없습니다");
        }
    }

    @Transactional
    public void updateAddress(Long userId, Long addressId, Address address) {
        Optional<User> foundUser = userRepository.findUserById(userId);
        if (foundUser.isPresent()) {
            List<Address> addressList = foundUser.get().getAddressList();
            if (!addressList.isEmpty()) {
                Address targetAddress = new Address("init", "init", "init");
                for (Address inList : addressList) {
                    if (inList.getId() == addressId) {
                        targetAddress = inList;
                    }
                    targetAddress.setCity(address.getCity());
                    targetAddress.setStreet(address.getStreet());
                    targetAddress.setZipcode(address.getZipcode());

                    addressRepository.save(inList);
                }
            } else {
                throw new IllegalStateException("등록된 주소가 없습니다");
            }
        } else {
            throw new IllegalStateException("유저를 찾을 수 없습니다");
        }
    }

    @Transactional
    public void changeMainAddress(Long userId, Long addressId) {
        Optional<User> foundUser = userRepository.findById(userId);
        if (foundUser.isPresent()) {
            //아마 프록시와 객체간 차이 때문에 못찾아낼듯 -> 리팩토링 필요
            List<Address> addressList = foundUser.get().getAddressList();
            for (Address address : addressList) {
                if (address.getMainAddress() == 1) {
                    address.setMainAddress(0);
                }
                if (address.getId() == addressId) {
                    address.setMainAddress(1);
                }
            }
        } else {
            throw new IllegalStateException("유저를 찾을 수 없습니다");
        }
    }

}
