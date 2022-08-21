package com.example.pickit.repository;

import com.example.pickit.domain.Address;
import com.example.pickit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAllById(Long addressId);

    Optional<Address> findById(Long addressId);

    Optional<Address> findByUser(User user);

    Optional<Address> findByMainAddressIs(int main);
}
