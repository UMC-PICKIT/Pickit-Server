package com.example.pickit.dto;

import com.example.pickit.domain.Menu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StoreInfoDto {
    private Long storeId;
    private String storeName;
    private String storeInfo; // 가게 한줄 소개
    private List<String> storeImgList;
    private int minPrice;
    private int maxPrice;
    private int deliveryTip;
//    private float averageReviewScore;
//    private int reviewCount;
    private ArrayList<Menu> menuList;
    // private ArrayList<String> bestMenuTopEight;
    // TODO: 머지 후에 성범오빠가 주문 카운트 해둔 값을 가져와서 합치기
}
