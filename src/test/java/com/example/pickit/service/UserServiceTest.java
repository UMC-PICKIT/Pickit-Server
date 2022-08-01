package com.example.pickit.service;

import com.example.pickit.domain.User;
import com.example.pickit.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Test
    public void 회원가입() throws Exception{
        //given
        User user = new User();
        user.setUserName("강성범");
        user.setNickName("똔봄");
        //when
        Long savedId = userService.join(user);
        //then

        Assert.assertEquals(user, userRepository.findUser(savedId));
     }

     @Test
     public void 회원정보_수정() throws Exception{
         //given

         //when

         //then
      }
}