package com.example.pickit.repository;

import com.example.pickit.domain.Store;
import com.example.pickit.domain.StoreImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreImageRepository extends JpaRepository<StoreImage, Long> {
    List<StoreImage> findByStore(Store store);
}
