package com.example.icetime.iceTimeApp.repository;

import com.example.icetime.iceTimeApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
