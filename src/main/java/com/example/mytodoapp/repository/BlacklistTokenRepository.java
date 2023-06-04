package com.example.mytodoapp.repository;

import com.example.mytodoapp.model.BlacklistToken;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface BlacklistTokenRepository extends JpaRepository<BlacklistToken, String> {
	boolean existsById(@NotNull String token);

	void deleteByexpirationDateBefore(LocalDateTime expirationDate);
}
