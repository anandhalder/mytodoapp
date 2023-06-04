package com.example.mytodoapp.services;

import java.time.LocalDateTime;

public interface TokenBlacklistService {

	public void addTokenToBlacklist(String token, LocalDateTime expiryDate);

	public boolean isTokenBlacklisted(String token);
}
