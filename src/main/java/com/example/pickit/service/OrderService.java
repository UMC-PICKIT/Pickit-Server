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
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    @Transactional
    public Long order(Long userId, Long menuId, int count) {
        Optional<User> foundUser = userRepository.findUserById(userId);

        if (foundUser.isPresent()) {
            Delivery delivery = new Delivery();

            Optional<Address> foundMainAddress = addressRepository.findByMainAddressIs(1);
            if (foundMainAddress.isPresent()) {
                delivery.setAddress(foundMainAddress.get());
                delivery.setStatus(DeliveryStatus.READY);
            }

        }
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
