package com.example.pickit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MenuDetailDto {
    private Long menuId;
    private String menuName;
    private String menuInfo;
    private String menuImageUrl;
}
