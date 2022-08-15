package com.example.pickit.repository;

import com.example.pickit.domain.OrderStatus;

public class OrderSearch {

    private String userName;
    private OrderStatus orderStatus;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
