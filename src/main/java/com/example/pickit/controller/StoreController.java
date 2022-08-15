package com.example.pickit.controller;

import com.example.pickit.config.BaseException;
import com.example.pickit.config.BaseResponse;
import com.example.pickit.dto.StoreInfoDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class StoreController {

    @GetMapping("/store/info/{storeId}")
    public BaseResponse<StoreInfoDto> storeInfo(@PathVariable("storeId") Long storeId) {
//        try{
//            StoreInfoDto storeInfoDto =
//            return new BaseResponse<>(announceResult);
//        } catch (BaseException exception) {
//            return new BaseResponse<>(exception.getStatus());
//        }
    }
}
