package com.example.pickit.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderTask> orderTaskList = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime orderDate;

    public void setUser(User client) {
        this.user = client;
        client.getOrderList().add(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    public void addOrderTask(OrderTask orderTask) {
        orderTaskList.add(orderTask);
        orderTask.setOrder(this);
    }

    public static Order createOrder(User user, Delivery delivery, OrderTask... orderTasks) {
        Order order = new Order();
        order.setUser(user);
        order.setDelivery(delivery);
        for (OrderTask orderTask : orderTasks) {
            order.addOrderTask(orderTask);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }

        this.setStatus(OrderStatus.CANCEL);
        for (OrderTask orderTask : orderTaskList) {
            orderTask.cancel();
        }
    }

    public int getTotalPrice() {
        return orderTaskList.stream().mapToInt(OrderTask::getTotalPrice).sum();
    }
}
