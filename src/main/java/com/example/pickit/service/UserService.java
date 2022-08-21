package com.example.pickit.service;

import com.example.pickit.config.BaseException;
import com.example.pickit.domain.User;
import com.example.pickit.dto.UserInfoDto;
import com.example.pickit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
            userRepository.save(user);
            return user.getId();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    private void validateDuplicateUser(User user) {
        List<User> findUserList = userRepository.findUserByUserName(user.getUserName());
        if (!findUserList.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 유저입니다.");
        }
    }

    @Transactional
    public void updateUserInfo(Long userId, String updatedNickName) throws BaseException{
        try {
            Optional<User> targetUser = userRepository.findUserById(userId);
            if (targetUser.isPresent()) {
                targetUser.get().setNickName(updatedNickName);
                userRepository.save(targetUser.get());
            } else {
                throw new IllegalStateException("유저를 찾을 수 없습니다");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional
    public void updateUserStatus(Long userId) throws BaseException {
        try {
            Optional<User> targetUser = userRepository.findUserById(userId);
            if (targetUser.isPresent()) {
                if (!targetUser.get().getStatus().equals("INACTIVE")) {
                    targetUser.get().setStatus("INACTIVE");
                    userRepository.save(targetUser.get());
                } else {
                    throw new IllegalStateException("이미 삭제된 유저입니다");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<User> findUsers(){
        return userRepository.findAll();
    }


    @Transactional
    public UserInfoDto findUser(Long userId) throws BaseException {
        try {
            Optional<User> foundUser = userRepository.findUserById(userId);
            if (foundUser.isPresent()) {
                UserInfoDto returnDto = new UserInfoDto(foundUser.get().getUserName(), foundUser.get().getNickName(), foundUser.get().getEmail(), foundUser.get().getPhone(), foundUser.get().getStatus());
                return returnDto;
            } else {
                throw new IllegalStateException("유저를 찾을 수 없습니다");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
