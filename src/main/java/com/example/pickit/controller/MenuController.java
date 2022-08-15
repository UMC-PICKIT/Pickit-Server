package com.example.pickit.controller;

import com.example.pickit.config.BaseException;
import com.example.pickit.config.BaseResponse;
import com.example.pickit.dto.MenuDetailDto;
import com.example.pickit.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/menu/{menuId}")
    public BaseResponse<MenuDetailDto> getMenuInfo(@PathVariable("menuId") long menuId) {
        try{
            MenuDetailDto menuDetailDto = menuService.getMenuDetail(menuId);
            return new BaseResponse<>(menuDetailDto);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
