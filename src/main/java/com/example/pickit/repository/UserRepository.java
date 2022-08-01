package com.example.pickit.repository;

import com.example.pickit.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    @PersistenceContext
    private final EntityManager em;

    public void saveUser(User user) {
        em.persist(user);
    }

    public User findUser(Long id) {
        return em.find(User.class, id);
    }

    public List<User> findAll() {
        return em.createQuery("select u from User u")
                .getResultList();
    }

    public List<User> findByName(String name) {
        return em.createQuery("select u from User u where u.userName= :name")
                .setParameter("name", name)
                .getResultList();
    }

    @Modifying(clearAutomatically = true)
    public void updateUserInfo(Long id, String updatedNickName) {
        //영속성 문제 발생할 가능성 있음 -> clearAutomatically로 해결될 지 의문
        em.createQuery("update User u set u.nickName = :nickName where u.id = :id")
                .setParameter("nickName", updatedNickName)
                .setParameter("id", id);
    }
}
