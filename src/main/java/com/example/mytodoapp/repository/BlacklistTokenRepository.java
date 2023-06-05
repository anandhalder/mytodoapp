package com.example.mytodoapp.repository;

import com.example.mytodoapp.model.BlacklistToken;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlacklistTokenRepository extends JpaRepository<BlacklistToken, String> {

	boolean existsById(@NotNull String token);

	void deleteByExpirationDateBefore(@NotNull java.time.LocalDateTime expiryDate);
}
