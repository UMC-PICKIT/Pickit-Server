package com.example.pickit.service;

import com.example.pickit.config.BaseException;
import com.example.pickit.domain.Menu;
import com.example.pickit.domain.Store;
import com.example.pickit.dto.MenuDetailDto;
import com.example.pickit.repository.MenuRepository;
import com.example.pickit.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.pickit.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private StoreRepository storeRepository;

    public ArrayList<MenuDetailDto> getStoreMenu(long shopId) throws BaseException {
        try{
            Store store = storeRepository.findByStoreId(shopId).get();
            List<Menu> menuList = menuRepository.findByStore(store);
            ArrayList<MenuDetailDto> menuDetailList = new ArrayList<>();
            for(Menu menu : menuList) {
                MenuDetailDto menuDetailDto = new MenuDetailDto(menu.getMenuId(), menu.getMenuName(), menu.getMenuInfo(), menu.getMenuImageUrl());
                menuDetailList.add(menuDetailDto);
            }
            return menuDetailList ;
        }  catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
