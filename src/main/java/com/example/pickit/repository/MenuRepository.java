package com.example.pickit.repository;

import com.example.pickit.domain.Menu;
import com.example.pickit.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    Optional<Menu> findByMenuId(long menuId);

    List<Menu> findByStore(Store store);
}
