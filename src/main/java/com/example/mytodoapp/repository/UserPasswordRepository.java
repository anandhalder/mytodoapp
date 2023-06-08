package com.example.mytodoapp.repository;

import com.example.mytodoapp.model.UserPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPasswordRepository extends JpaRepository<UserPassword, Long> {

	Optional<UserPassword> findByUserId(Long userId);
}
