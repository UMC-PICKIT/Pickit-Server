package com.example.pickit.service;

import com.example.pickit.domain.*;
import com.example.pickit.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final MenuRepository menuRepository;
    private final AddressRepository addressRepository;

    @Transactional
    public Long order(Long userId, Long menuId, int count) {
        Optional<User> foundUser = userRepository.findUserById(userId);
        Optional<Menu> foundMenu = menuRepository.findByMenuId(menuId);

        if (foundUser.isPresent() && foundMenu.isPresent()) {
            Delivery delivery = new Delivery();

            Optional<Address> foundMainAddress = addressRepository.findByMainAddressIs(1);
            if (foundMainAddress.isPresent()) {
                delivery.setAddress(foundMainAddress.get());
                delivery.setStatus(DeliveryStatus.READY);
            }

            OrderTask orderTask = OrderTask.createOrderTask(foundMenu.get(), foundMenu.get().getMenuPrice(), count);
            foundMenu.get().addOrderCount(count);
            Order order = Order.createOrder(foundUser.get(), delivery, orderTask);

            orderRepository.save(order);
            return order.getId();
        } else {
            throw new IllegalStateException("유저 및 메뉴가 존재하지 않습니다");
        }
    }


    @Transactional
    public void cancelOrder(Long orderId) {
        Optional<Order> foundOrder = orderRepository.findOrderById(orderId);
        if (foundOrder.isPresent()) {
            foundOrder.get().cancel();
        }
    }

//    public List<Order> findOrders(OrderSearch orderSearch) {
//        return orderRepository.findAllByCriteria(orderSearch);
//    }
}
