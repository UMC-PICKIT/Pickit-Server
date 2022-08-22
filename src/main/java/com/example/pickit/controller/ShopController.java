package com.example.pickit.controller;

import com.example.pickit.config.BaseException;
import com.example.pickit.config.BaseResponse;
import com.example.pickit.dto.MenuDetailDto;
import com.example.pickit.dto.StoreInfoDto;
import com.example.pickit.dto.StoreSimpleDto;
import com.example.pickit.service.MenuService;
import com.example.pickit.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ShopController {
    @Autowired
    private StoreService storeService;
    @Autowired
    private MenuService menuService;

//    @GetMapping("/shop/{shopId}")
//    public BaseResponse<StoreInfoDto> storeInfo(@PathVariable("storeId") Long storeId) {
//        try{
//            StoreInfoDto storeInfoDto = storeService.getStoreInfo(storeId);
//            return new BaseResponse<>(storeInfoDto);
//        } catch (BaseException exception) {
//            return new BaseResponse<>(exception.getStatus());
//        }
//    }

    @GetMapping("/shop/{shopId}/menu")
    public BaseResponse<ArrayList<MenuDetailDto>> storeMenuList(@PathVariable("shopId") Long shopId) {
        try {
            ArrayList<MenuDetailDto> menuDetailDtos = menuService.getStoreMenu(shopId);
            return new BaseResponse<>(menuDetailDtos);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

//    @GetMapping("/shop/{shopId}/detail")
//    public BaseResponse<StoreSimpleDto> storeDetail(@PathVariable("shopId") Long shopId) {
//        try {
//            StoreSimpleDto storeSimpleDto = storeService.getStoreSimpleInfo(shopId);
//            return new BaseResponse<>(storeSimpleDto);
//        } catch (BaseException exception) {
//            return new BaseResponse<>(exception.getStatus());
//        }
//    }
}
