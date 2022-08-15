package com.example.pickit.service;

import com.example.pickit.config.BaseException;
import com.example.pickit.config.BaseResponseStatus;
import com.example.pickit.domain.Store;
import com.example.pickit.domain.StoreImage;
import com.example.pickit.dto.StoreInfoDto;
import com.example.pickit.repository.StoreImageRepository;
import com.example.pickit.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import static com.example.pickit.config.BaseResponseStatus.*;


@Service
public class StoreService {

    private StoreRepository storeRepository;
    private StoreImageRepository storeImageRepository;

//    public StoreInfoDto getStoreInfo(long storeId) throws BaseException {
//        Optional<Store> store = storeRepository.findByStoreId(storeId);
//        if (!store.isPresent()) {
//            throw new BaseException(NO_SHOP_INFO);
//        }
//        List<StoreImage> storeImageList =
//    }
}
