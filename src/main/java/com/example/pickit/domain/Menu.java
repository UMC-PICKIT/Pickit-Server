package com.example.pickit.domain;

import com.example.pickit.exception.NotEnoughStockException;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Data
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

    private int orderCount = 0;

    private int stockQuantity;

    public void addOrderCount(int orderCount) {
        this.orderCount += orderCount;
    }

    public void removeOrderCount(int orderCount) {
        this.orderCount -= orderCount;
    }

    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
