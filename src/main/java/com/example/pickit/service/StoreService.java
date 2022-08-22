package com.example.pickit.service;

import com.example.pickit.config.BaseException;
import com.example.pickit.domain.Store;
import com.example.pickit.domain.StoreImage;
import com.example.pickit.dto.StoreSimpleDto;
import com.example.pickit.repository.StoreImageRepository;
import com.example.pickit.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import static com.example.pickit.config.BaseResponseStatus.*;


@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private StoreImageRepository storeImageRepository;

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

//
//    public StoreInfoDto getStoreInfo(Long storeId) throws BaseException {
////        Optional<Store> store = storeRepository.findByStoreId(storeId);
////
////        if (store.isEmpty()) {
////            throw new BaseException(NO_SHOP_INFO);
////        }
////
////        Store storeData = store.get();
////
////        List<StoreImage> storeImage = storeImageRepository.findByStore(store.get());
////        List<String> storeImgUrlList = new ArrayList();
////        for(StoreImage img : storeImage) {
////            storeImgUrlList.add(img.getImageUrl());
////        }
////
////
////
////        StoreInfoDto storeInfoDto = new StoreInfoDto(storeData.getStoreName(), storeData.getStoreCallNum(), storeData.getStoreInfo(), storeData.getDeliveryTip(), storeData.getStoreAddress(), storeData.getStoreDescription(), storeImgUrlList);
////        return storeInfoDto;
//        return null;
//    }
}
