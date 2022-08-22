package com.example.pickit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StoreSimpleDto {
    private String storeName;
    private String storeDescription;
    private List<String> storeImg;
}
