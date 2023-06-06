package com.example.mytodoapp.Utils;

import com.example.mytodoapp.services.TokenBlacklistService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TokenCleanupTask {

	private final TokenBlacklistService blacklistTokenRepository;

	public TokenCleanupTask(TokenBlacklistService blacklistTokenRepository) {
		this.blacklistTokenRepository = blacklistTokenRepository;
	}

	@Scheduled(cron = "0 * * * * *")
	public void cleanUpTokens() {
		blacklistTokenRepository.deleteByExpiryDateBefore(java.time.LocalDateTime.now());
	}
}
