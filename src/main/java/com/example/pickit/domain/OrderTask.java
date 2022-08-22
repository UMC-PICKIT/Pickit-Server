package com.example.pickit.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class OrderTask {

    @Id
    @GeneratedValue
    @Column(name = "order_task_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;
    private int count;


    public static OrderTask createOrderTask(Menu menu, int orderPrice, int count) {
        OrderTask orderTask = new OrderTask();
        orderTask.setMenu(menu);
        orderTask.setOrderPrice(orderPrice);
        orderTask.setCount(count);

        menu.removeStock(count);

        return orderTask;
    }

    public void cancel() {
        getMenu().addStock(count);
        getMenu().removeOrderCount(count);
    }

    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
