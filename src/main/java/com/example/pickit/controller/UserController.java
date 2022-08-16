package com.example.pickit.controller;

import com.example.pickit.config.BaseException;
import com.example.pickit.config.BaseResponse;
import com.example.pickit.config.BaseResponseStatus;
import com.example.pickit.domain.Address;
import com.example.pickit.domain.User;
import com.example.pickit.dto.UserInfoDto;
import com.example.pickit.service.AddressService;
import com.example.pickit.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/user/{userId}")
    public BaseResponse<UserInfoDto> getUserInfo(@PathVariable("userId") Long userId) {
        try {
            UserInfoDto userInfoDto = userService.findUser(userId);
            return new BaseResponse<>(userInfoDto);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @PostMapping(value = "/user")
    public BaseResponse<UserInfoDto> saveUser(@RequestBody User user) {
        try {
            userService.join(user);
            return new BaseResponse<>(BaseResponseStatus.USER_SAVE_SUCCESS);
        } catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

    @PatchMapping(value = "/user/delete/{userId}")
    public BaseResponse<UserInfoDto> deleteUser(@PathVariable("userId") Long userId) {
        try {
            userService.updateUserStatus(userId);
            return new BaseResponse<>(BaseResponseStatus.USER_DELETE_SUCCESS);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @PatchMapping(value = "/user/update/{userId}")
    public BaseResponse<UserInfoDto> updateUser(@PathVariable("userId") Long userId, @RequestBody String updatedUserName) {
        try {
            userService.updateUserInfo(userId, updatedUserName);
            return new BaseResponse<>(BaseResponseStatus.USER_UPDATE_SUCCESS);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}
