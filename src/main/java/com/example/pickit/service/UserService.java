package com.example.pickit.service;

import com.example.pickit.config.BaseException;
import com.example.pickit.config.BaseResponseStatus;
import com.example.pickit.domain.User;
import com.example.pickit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.pickit.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService{

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
            throw new IllegalStateException("이미 존재하는 유저입니다.");
        }
    }

    @Transactional
    public void updateUserInfo(Long userId, String updatedNickName) {
        userRepository.updateUserInfo(userId, updatedNickName);
    }

    @Transactional
    public void updateUserStatus(Long userId) {
        userRepository.updateUserStatus(userId);
    }

    public List<User> findUsers(){
        return userRepository.findAll();
    }

    public User findOne(Long id) throws BaseException {
        try {
            return userRepository.findUser(id);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
