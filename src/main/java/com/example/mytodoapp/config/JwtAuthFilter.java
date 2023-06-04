package com.example.mytodoapp.config;

import com.example.mytodoapp.services.DatabaseTokenBlacklistService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	private final UserDetailsService userDetailsService;
	private final JwtUtil jwtUtil;
	private final DatabaseTokenBlacklistService databaseTokenBlacklistService;

	public JwtAuthFilter(UserDetailsService userDetailsService, JwtUtil jwtUtil, DatabaseTokenBlacklistService databaseTokenBlacklistService) {
		this.userDetailsService = userDetailsService;
		this.jwtUtil = jwtUtil;
		this.databaseTokenBlacklistService = databaseTokenBlacklistService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		// Get Token from Request
		String token = getTokenFromRequest(request);
		// ! this checking logic is getting heavy as per my understanding.
		if (token != null && !databaseTokenBlacklistService.isTokenBlacklisted(token) && jwtUtil.validateToken(token)) {
			// Get Username from Token
			String username = jwtUtil.getUsernameFromToken(token);
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			// Set Authentication in Context
			SecurityContextHolder.getContext().setAuthentication(jwtUtil.getAuthentication(userDetails, request));
		}

		filterChain.doFilter(request, response);
	}

	public String getTokenFromRequest(HttpServletRequest request) {
		// Extract Authentication Header
		var authHeader = request.getHeader("Authorization");

		// Bearer <token>
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7);
		}

		return null;
	}
}
