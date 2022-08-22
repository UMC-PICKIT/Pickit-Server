package com.example.pickit.repository;

import com.example.pickit.domain.Order;
import com.example.pickit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findOrderById(Long orderId);

    List<Order> findALlByUser(User user);
}
