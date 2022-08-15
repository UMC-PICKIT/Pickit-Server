package com.example.pickit.controller;

import com.example.pickit.config.BaseException;
import com.example.pickit.config.BaseResponse;
import com.example.pickit.domain.Address;
import com.example.pickit.domain.User;
import com.example.pickit.dto.UserInfoDto;
import com.example.pickit.service.AddressService;
import com.example.pickit.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/user/{userId}")
    public BaseResponse<UserInfoDto> getUserInfo(@PathVariable("userId") Long userId) {
        try {
            User foundUser = userService.findOne(userId);
            UserInfoDto returnUser = new UserInfoDto();

            returnUser.setUserName(foundUser.getUserName());
            returnUser.setNickName(foundUser.getNickName());
            returnUser.setEmail(foundUser.getEmail());
            returnUser.setPhone(foundUser.getPhone());
            returnUser.setAddressList(foundUser.getAddressList());
            returnUser.setOrderList(foundUser.getOrderList());
            returnUser.setStatus(foundUser.getStatus());

            return new BaseResponse<>(returnUser);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

//    @GetMapping(value = "/user/{userId}")
//    public String getUserInfo(@PathVariable("userId") Long userId) {
//
//        try {
//            User findUser = userService.findOne(userId);
//
//        } catch (BaseException exception) {
//
//        }
//        System.out.println("findUser.getUserName() = " + findUser.getUserName());
////        return findUser.toString();
//        return "유저 조회 완료";
//    }

    @PostMapping(value = "/user")
    public String saveUser(@RequestBody User user) {
        user.setStatus("ACTIVE");
        user.setCreatedAt(LocalDateTime.now());
        userService.join(user);

//        return user.getId();
        return "유정 등록 완료";
    }

    @PatchMapping(value = "/user/delete/{userId}")
    public String deleteUser(@PathVariable("userId") Long userId) {
        userService.updateUserStatus(userId);

        return "유저 삭제 완료";
    }

    @PatchMapping(value = "/user/update/{userId}")
    public String updateUser(@PathVariable("userId") Long userId, @RequestBody String updatedUserName) {
        userService.updateUserInfo(userId, updatedUserName);

        return "유저 정보 수정 완료";
    }


}
