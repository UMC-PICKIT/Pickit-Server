package com.example.pickit.dto;

import com.example.pickit.domain.Address;
import com.example.pickit.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserInfoDto {
    private String userName;
    private String nickName;
    private String email;
    private String phone;
    private String status;
}
