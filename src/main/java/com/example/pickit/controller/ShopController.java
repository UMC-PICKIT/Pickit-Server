package com.example.pickit.controller;

import com.example.pickit.config.BaseException;
import com.example.pickit.config.BaseResponse;
import com.example.pickit.dto.StoreInfoDto;
import com.example.pickit.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopController {
    @Autowired
    private StoreService storeService;
    @GetMapping("/shop/{shopId}")
    public BaseResponse<StoreInfoDto> storeInfo(@PathVariable("storeId") Long storeId) {
        try{
            StoreInfoDto storeInfoDto = storeService.getStoreInfo(storeId);
            return new BaseResponse<>(storeInfoDto);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
