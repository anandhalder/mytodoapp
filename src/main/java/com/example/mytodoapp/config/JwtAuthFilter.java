package com.example.mytodoapp.config;

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

	public JwtAuthFilter(UserDetailsService userDetailsService, JwtUtil jwtUtil) {
		this.userDetailsService = userDetailsService;
		this.jwtUtil = jwtUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		// Get Token from Request
		String token = getTokenFromRequest(request);
		// Validate Token
		if (token != null && jwtUtil.validateToken(token)) {
			// Get Username from Token
			String username = jwtUtil.getUsernameFromToken(token);
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			System.out.println(" ***************** UserDetails: ***************** " + userDetails);
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
