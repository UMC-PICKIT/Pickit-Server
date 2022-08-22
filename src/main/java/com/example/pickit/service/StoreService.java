package com.example.pickit.service;

import com.example.pickit.config.BaseException;
import com.example.pickit.domain.Menu;
import com.example.pickit.domain.Store;
import com.example.pickit.domain.StoreImage;
import com.example.pickit.dto.StoreInfoDto;
import com.example.pickit.dto.StoreSimpleDto;
import com.example.pickit.repository.MenuRepository;
import com.example.pickit.repository.StoreImageRepository;
import com.example.pickit.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.pickit.config.BaseResponseStatus.*;


@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private StoreImageRepository storeImageRepository;
    @Autowired
    private MenuRepository menuRepository;

    public StoreSimpleDto getStoreSimpleInfo(Long storeId) throws BaseException {
        try{
            Store store = storeRepository.findByStoreId(storeId).get();
            List<StoreImage> storeImageList = storeImageRepository.findByStore(store);
            List<String> storeUrlList = new ArrayList<>();
            for (StoreImage image : storeImageList) {
                storeUrlList.add(image.getImageUrl());
            }
            return new StoreSimpleDto(store.getStoreName(),store.getStoreInfo(),storeUrlList);
        }  catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }


    public StoreInfoDto getStoreInfo(Long storeId) throws BaseException {
        Optional<Store> store = storeRepository.findByStoreId(storeId);

        if (store.isEmpty()) {
            throw new BaseException(NO_SHOP_INFO);
        }
        Store storeData = store.get();

        List<StoreImage> storeImage = storeImageRepository.findByStore(store.get());
        List<String> storeImgUrlList = new ArrayList();
        for(StoreImage img : storeImage) {
            storeImgUrlList.add(img.getImageUrl());
        }

        List<Menu> menuList = menuRepository.findByStore(storeData);
        List<Integer> menuPriceList = new ArrayList();
        for(Menu menu : menuList) {
            menuPriceList.add(menu.getMenuPrice());
        }

        int maxPrice = Collections.max(menuPriceList);
        int minPrice = Collections.min(menuPriceList);

        StoreInfoDto storeInfoDto = new StoreInfoDto(storeData.getStoreId(),storeData.getStoreName(), storeData.getStoreInfo(), storeImgUrlList, minPrice, maxPrice, storeData.getDeliveryTip(),(ArrayList<Menu>) menuList);
        return storeInfoDto;
    }
}
