package com.example.pickit.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String email;
    private String phone;
    private String nickName;
    private String userName;

    @Embedded
    private Address address;
    @Column(insertable = false, updatable = false)
    private String user_status;


    public User() {
        this.user_status = "ACTIVE";
    }
}

