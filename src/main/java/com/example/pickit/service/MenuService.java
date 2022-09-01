package com.example.pickit.service;

import com.example.pickit.config.BaseException;
import com.example.pickit.domain.Menu;
import com.example.pickit.domain.Store;
import com.example.pickit.dto.MenuDetailDto;
import com.example.pickit.repository.MenuRepository;
import com.example.pickit.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.pickit.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private StoreRepository storeRepository;

    public MenuDetailDto getMenuDetail(long menuId) throws BaseException {
        Menu testMenu = new Menu();
        Optional<Store> byStoreId = storeRepository.findByStoreId(1l);
        testMenu.setMenuName("123");
        testMenu.setMenuPrice(10000);
        testMenu.setMenuInfo("12345");
        testMenu.setStore(byStoreId.get());
        menuRepository.save(testMenu);

        try{
            Optional<Menu> menuInfo = menuRepository.findByMenuId(menuId);
            Menu menu = menuInfo.get();
            MenuDetailDto menuDetailDto = new MenuDetailDto(menuId,menu.getMenuName(), menu.getMenuImageUrl(), menu.getBestMenu(), menu.getSoldOut(), menu.getMenuPrice(), menu.getMenuInfo(), menu.getCookingTime(), menu.getPerson_amount(), menu.getStockQuantity(), menu.getStore().getDeliveryTip(), menu.getStore().getStoreName());
            return menuDetailDto;
        }  catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
