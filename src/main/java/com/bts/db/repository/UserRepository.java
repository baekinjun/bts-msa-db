package com.bts.db.repository;

import com.bts.db.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndSocial(String email, String social);
    Optional<User> findById(Long id);
    Optional<User> findByuserId(Long id);
    Optional<User> findByemail(String email);
}
