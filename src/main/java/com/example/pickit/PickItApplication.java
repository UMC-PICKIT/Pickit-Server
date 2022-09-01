package com.example.pickit;

import com.example.pickit.domain.User;
import com.example.pickit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.*;

@SpringBootApplication
@RequiredArgsConstructor
public class PickItApplication {
    public static void main(String[] args) {
        SpringApplication.run(PickItApplication.class, args);
    }

}
