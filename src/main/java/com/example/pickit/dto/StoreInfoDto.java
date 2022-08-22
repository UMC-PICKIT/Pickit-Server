package com.example.pickit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StoreInfoDto {
    private String storeName;
    private String storeDescription;
    private List<String> storeImgList;
    private float averageReviewScore;
    private int reviewCount;
    private int minPrice;
    private int maxPrice;
    // private ArrayList<String> bestMenuTopEight;
    // TODO: 성범오빠가 주문 조회해서 서비스 받으면 합치기.
}
