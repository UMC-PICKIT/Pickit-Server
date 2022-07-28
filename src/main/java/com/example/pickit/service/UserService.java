package com.example.pickit.service;

import com.example.pickit.domain.User;
import com.example.pickit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long join(User user) {
        validateDuplicateUser(user);
        userRepository.saveUser(user);
        return user.getId();
    }

    private void validateDuplicateUser(User user) {
        List<User> findUserList = userRepository.findByName(user.getUserName());
        if (!findUserList.isEmpty()) {
            userRepository.saveUser(user);
        } else {
            throw new IllegalStateException("이미 존재하는 유저입니다.");
        }
    }

    public List<User> findUsers(){
        return userRepository.findAll();
    }

    public User findOne(Long id) {
        return userRepository.findOne(id);
    }
}
