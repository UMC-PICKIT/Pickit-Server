package com.example.pickit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StoreInfoDto {
    private String storeName;
    private String storeCallNum;
    private String storeInfo;
    private int deliveryTip;
    private String storeAddress;
    private String storeDescription;
    private List<String> storeImgList;
}
