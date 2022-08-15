package com.example.pickit.controller;

import com.example.pickit.domain.Address;
import com.example.pickit.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    @PostMapping(value = "/{userId}/add")
    public String addAddress(@PathVariable("userId") Long userId, @RequestBody Address address) {
        String city = address.getCity();
        String street = address.getStreet();
        String zipcode = address.getZipcode();
        addressService.saveAddress(userId, city, street, zipcode);

        return "주소 등록 완료";
    }

    @PatchMapping(value = "/{userId}/delete")
    public String deleteAddress(@PathVariable("userId") Long userId) {
        addressService.deleteAddress(userId);

        return "주소 삭제 완료";
    }

    @PatchMapping(value = "/{userId}/update/{addressId}")
    public String updateAddress(@PathVariable("userId") Long userId, @PathVariable("addressId") Long addressId, @RequestBody Address address) {
        addressService.updateAddress(userId, addressId, address);

        return "주소 수정 완료";
    }

    @PatchMapping(value = "/{userId}/change/{addressId}")
    public String changMainAddress(@PathVariable("userId") Long userId,@PathVariable("addressId") Long addressId) {
        addressService.changeMainAddress(userId, addressId);

        return "대표 주소 변경 완료";
    }
}
