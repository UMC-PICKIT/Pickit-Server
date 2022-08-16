package com.example.pickit.repository;

import com.example.pickit.domain.Address;
import com.example.pickit.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AddressRepository{

    @PersistenceContext
    EntityManager em;

    public void saveAddress(Address address) {
        em.persist(address);
    }

    public List<Address> getAllAddress(Long userId) {
        return em.createQuery("select a from Address a where a.user = :user")
                .setParameter("user", em.find(User.class, userId))
                .getResultList();
    }

    public Address findAddress(Long id) {
        return em.find(Address.class, id);
    }

    public List<Address> findAllAddressByUserId(Long userId) {
        return em.createQuery("select a from Address a where a.user.id= : userId")
                .setParameter("userId", userId)
                .getResultList();
    }

    @Modifying(clearAutomatically = true)
    public void updateAddressStatus(Long id) {
        Address foundAddress = findAddress(id);
        if (foundAddress.getAddressStatus().equals("INACTIVE")) {
            throw new IllegalStateException("이미 삭제된 주소입니다");
        }else{
            foundAddress.setAddressStatus("INACTIVE");
            em.persist(foundAddress);
        }
    }

    public void updateAddressDetails(Long userId, Long addressId, Address updatedAddress) {
        Address targetAddress = (Address) em.createQuery("select a from Address a where a.id = :addressId and a.user.id = :userId")
                .setParameter("addressId", addressId)
                .setParameter("userId", userId)
                .getSingleResult();

        targetAddress.setCity(updatedAddress.getCity());
        targetAddress.setStreet(updatedAddress.getStreet());
        targetAddress.setZipcode(updatedAddress.getZipcode());

        em.persist(targetAddress);
    }

    public void changeMainAddress(Long userId, Long addressId) {
        Address pastMainAddress = (Address) em.createQuery("select a from Address a where a.user.id = :userId and a.mainAddress = :temp")
                .setParameter("userId", userId)
                .setParameter("temp", 1)
                .getSingleResult();

        pastMainAddress.setMainAddress(0);
        Address targetAddress = em.find(Address.class, addressId);
        targetAddress.setMainAddress(1);

        em.persist(pastMainAddress);
        em.persist(targetAddress);
    }

    public Address getMainAddress(Long userId) {
        return (Address) em.createQuery("select a from Address a where a.mainAddress = 1 and a.user.id= :userId")
                .setParameter("userId", userId)
                .getSingleResult();
    }
}
