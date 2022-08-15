package com.example.pickit.service;

import com.example.pickit.config.BaseException;
import com.example.pickit.domain.Menu;
import com.example.pickit.dto.MenuDetailDto;
import com.example.pickit.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.pickit.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    public MenuDetailDto getMenuDetail(long menuId) throws BaseException {
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
