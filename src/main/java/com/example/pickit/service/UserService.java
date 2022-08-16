package com.example.pickit.service;

import com.example.pickit.config.BaseException;
import com.example.pickit.domain.User;
import com.example.pickit.dto.UserInfoDto;
import com.example.pickit.repository.AddressRepository;
import com.example.pickit.repository.OrderRepository;
import com.example.pickit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.pickit.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService{

    private final UserRepository userRepository;
    @Transactional
    public Long join(User user) throws BaseException{
        try {
            validateDuplicateUser(user);
            user.setStatus("ACTIVE");
            user.setCreatedAt(LocalDateTime.now());
            userRepository.saveUser(user);
            return user.getId();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    private void validateDuplicateUser(User user) {
        List<User> findUserList = userRepository.findByName(user.getUserName());
        if (!findUserList.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 유저입니다.");
        }
    }

    @Transactional
    public void updateUserInfo(Long userId, String updatedNickName) throws BaseException{
        try {
            userRepository.updateUserInfo(userId, updatedNickName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional
    public void updateUserStatus(Long userId) throws BaseException {
        try {
            userRepository.updateUserStatus(userId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
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

    @Transactional
    public UserInfoDto findUser(Long userId) throws BaseException {
        try {
            User foundUser = userRepository.findUser(userId);
            UserInfoDto returnDto = new UserInfoDto(foundUser.getUserName(), foundUser.getNickName(), foundUser.getEmail(), foundUser.getPhone(),foundUser.getStatus());
            return returnDto;

        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
