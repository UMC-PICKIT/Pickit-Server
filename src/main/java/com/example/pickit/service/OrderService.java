package com.example.pickit.service;

import com.example.pickit.domain.*;
import com.example.pickit.repository.AddressRepository;
import com.example.pickit.repository.OrderRepository;
import com.example.pickit.repository.OrderSearch;
import com.example.pickit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    @Transactional
    public Long order(Long userId, Long menuId, int count) {
        User foundUser = userRepository.findUser(userId);

        Delivery delivery = new Delivery();
        delivery.setAddress(addressRepository.getMainAddress(foundUser.getId()));
        delivery.setStatus(DeliveryStatus.READY);

        //menuRepository 완료되어야 진행 가능.

        return 1L;
    }


    @Transactional
    public void cancelOrder(Long orderId) {
        Order foundOrder = orderRepository.findOne(orderId);
        foundOrder.cancel();
    }

    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAllByCriteria(orderSearch);
    }
}
