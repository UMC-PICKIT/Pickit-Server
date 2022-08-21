package com.example.pickit.repository;

import com.example.pickit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserById(Long userId);

    List<User> findUserByUserName(String name);

    @Modifying(clearAutomatically = true)
    public static void updateUserStatus(Long id){

    }
}
