package com.example.mytodoapp.services.Impl;

import com.example.mytodoapp.model.BlacklistToken;
import com.example.mytodoapp.repository.BlacklistTokenRepository;
import com.example.mytodoapp.services.TokenBlacklistService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TokenBlacklistServiceImpl implements TokenBlacklistService {

	private final BlacklistTokenRepository blacklistTokenRepository;

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
