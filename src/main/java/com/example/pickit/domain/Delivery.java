package com.example.pickit.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "delivery")
    private Order order;

    @OneToOne(fetch = FetchType.LAZY)
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
}
