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
    private String menuImageUrl;
    private Boolean bestMenu;
    private Boolean soldOut;
    private int menuPrice;
    private String menuInfo;
    private int cookingTime;
    private int person_amount;
    private int stockQuantity;
    private int storeDeliveryPrice;
    private String storeName;
}
