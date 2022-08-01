package com.example.pickit.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Menu extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long menuId;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    private String menuName;

    private String menuImageUrl;

    private Boolean bestMenu;

    private Boolean soldOut;

    private int menuPrice;

    private String menuInfo;

    private int cookingTime;

    private int person_amount;

    private String status;

}