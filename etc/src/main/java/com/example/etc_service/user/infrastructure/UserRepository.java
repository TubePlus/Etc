package com.example.etc_service.user.infrastructure;


import com.example.etc_service.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
