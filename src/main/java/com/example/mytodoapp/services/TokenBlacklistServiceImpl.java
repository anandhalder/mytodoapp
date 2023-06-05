package com.example.mytodoapp.services;

import com.example.mytodoapp.model.BlacklistToken;
import com.example.mytodoapp.repository.BlacklistTokenRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TokenBlacklistServiceImpl implements TokenBlacklistService {

	private final BlacklistTokenRepository blacklistTokenRepository;

	public TokenBlacklistServiceImpl(BlacklistTokenRepository blacklistTokenRepository) {
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

	@Override
	@Transactional
	public void deleteByExpiryDateBefore(LocalDateTime expiryDate) {
		blacklistTokenRepository.deleteByExpirationDateBefore(expiryDate);
	}
}
