package com.example.pickit.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreImage extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_image_id")
    private Long storeImageId;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    private String imageUrl;

    private int mainImage; // 1: 메인이미지, 0: 기타 이미지

    private String status;

}
