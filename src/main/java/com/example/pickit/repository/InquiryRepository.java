package com.example.pickit.repository;

import com.example.pickit.domain.Inquiry;
import com.example.pickit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

    List<Inquiry> findAllByUser(User user);

    Optional<Inquiry> findById(Long inquiryId);

}
