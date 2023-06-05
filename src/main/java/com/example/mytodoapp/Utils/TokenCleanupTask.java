package com.example.mytodoapp.Utils;

import com.example.mytodoapp.repository.BlacklistTokenRepository;
import org.springframework.stereotype.Component;

@Component
public class TokenCleanupTask {

	private final BlacklistTokenRepository blacklistTokenRepository;

	public TokenCleanupTask(BlacklistTokenRepository blacklistTokenRepository) {
		this.blacklistTokenRepository = blacklistTokenRepository;
	}

	//	@Scheduled(fixedRate = 10 * 1000)
	public void cleanUpTokens() {
//		blacklistTokenRepository.deleteByExpiryDateBefore(java.time.LocalDateTime.now());
	}
}
