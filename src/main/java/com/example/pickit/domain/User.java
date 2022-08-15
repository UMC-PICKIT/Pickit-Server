package com.example.pickit.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String nickName;
    private String userName;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "user")
    private List<Address> addressList = new ArrayList<>();

    @OneToMany(mappedBy = "client")
    private List<Order> orderList = new ArrayList<>();

    private String userStatus;

    @Column(updatable = false)
    private LocalDateTime userCreatedAt;

}

