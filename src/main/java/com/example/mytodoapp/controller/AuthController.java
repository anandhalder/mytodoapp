package com.example.mytodoapp.controller;

import com.example.mytodoapp.config.JwtUtil;
import com.example.mytodoapp.dto.AuthRequest;
import com.example.mytodoapp.services.TokenBlacklistService;
import com.example.mytodoapp.services.TokenBlacklistServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final UserDetailsService userDetailsService;
	private final JwtUtil jwtUtil;
	private final TokenBlacklistServiceImpl tokenBlacklistServiceImpl;
	private final TokenBlacklistService tokenBlacklistService;

	public AuthController(UserDetailsService userDetailsService, JwtUtil jwtUtil, TokenBlacklistServiceImpl tokenBlacklistServiceImpl, TokenBlacklistService tokenBlacklistService) {
		this.userDetailsService = userDetailsService;
		this.jwtUtil = jwtUtil;
		this.tokenBlacklistServiceImpl = tokenBlacklistServiceImpl;
		this.tokenBlacklistService = tokenBlacklistService;
	}

	@GetMapping("/login")
	public String getToken(@RequestBody AuthRequest authRequest) throws Exception {
		UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

		if (userDetails.getPassword().equals(authRequest.getPassword())) {
			return jwtUtil.generateToken(userDetails);
		}

		throw new Exception("Invalid username or password");
	}

	@PostMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {

		// Get the token from the header
		String token = request.getHeader("Authorization").split(" ")[1];
		// Add the Token into the blacklist
		tokenBlacklistServiceImpl.addTokenToBlacklist(token, jwtUtil.getJwtExpirationInMillis(token));
		return "Logout Successful";
	}

	@GetMapping("/test")
	public void test() {
		System.out.println("Inside Test method :-> " + LocalDateTime.now());
		tokenBlacklistService.deleteByExpiryDateBefore(LocalDateTime.now());
	}
}
