package com.example.pickit;

import com.example.pickit.domain.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.*;

@SpringBootApplication
public class PickItApplication {
    public static void main(String[] args) {
        SpringApplication.run(PickItApplication.class, args);
    }

}
