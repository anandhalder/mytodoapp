package com.example.mytodoapp.repository;

import com.example.mytodoapp.model.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByUsername(String username);

	@NotNull Page<User> findAll(@NotNull Pageable pageable);

	Optional<User> findByUsername(String username);
}
