package com.example.pickit.domain;

import lombok.*;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long storeId;

    private String storeName;

    private String storeCallNum;

    private String storeInfo;

    private int deliveryTip;

    private String storeAddress;

    private String storeDescription;

    private String status;
}
