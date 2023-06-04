package com.example.mytodoapp.controller;

import com.example.mytodoapp.config.JwtUtil;
import com.example.mytodoapp.dto.AuthRequest;
import com.example.mytodoapp.services.DatabaseTokenBlacklistService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final UserDetailsService userDetailsService;
	private final JwtUtil jwtUtil;
	private final DatabaseTokenBlacklistService databaseTokenBlacklistService;

	public AuthController(UserDetailsService userDetailsService, JwtUtil jwtUtil, DatabaseTokenBlacklistService databaseTokenBlacklistService) {
		this.userDetailsService = userDetailsService;
		this.jwtUtil = jwtUtil;
		this.databaseTokenBlacklistService = databaseTokenBlacklistService;
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
		databaseTokenBlacklistService.addTokenToBlacklist(token, jwtUtil.getJwtExpirationInMillis(token));
		return "Logout Successful";
	}
}
