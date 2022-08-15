package com.example.pickit.service;

import com.example.pickit.domain.Basket;
import com.example.pickit.domain.Menu;
import com.example.pickit.domain.User;
import com.example.pickit.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BasketService {

    private final BasketRepository basketRepository;

    @Transactional
    public Basket saveBasket(Menu menu, User user) {
        Basket basket = new Basket();
        basket.setUser(user);
        basket.setMenu(menu);

        return basketRepository.save(basket);
    }

    @Transactional
    public void deleteFromBasket(Long basketId) {

        basketRepository.deleteById(basketId);
    }

    public List<Basket> findAllMenu(Long basketId) {
        return basketRepository.findAll();
    }

}
