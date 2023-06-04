package com.example.mytodoapp.services;

import com.example.mytodoapp.model.BlacklistToken;
import com.example.mytodoapp.repository.BlacklistTokenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DatabaseTokenBlacklistService implements TokenBlacklistService {

	private final BlacklistTokenRepository blacklistTokenRepository;

	public DatabaseTokenBlacklistService(BlacklistTokenRepository blacklistTokenRepository) {
		this.blacklistTokenRepository = blacklistTokenRepository;
	}

	@Override
	public void addTokenToBlacklist(String token, LocalDateTime expirationDate) {
		blacklistTokenRepository.save(new BlacklistToken(token, expirationDate));
	}

	@Override
	public boolean isTokenBlacklisted(String token) {
		return blacklistTokenRepository.existsById(token);
	}
}
