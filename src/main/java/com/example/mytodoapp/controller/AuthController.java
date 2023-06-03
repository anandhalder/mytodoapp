package com.example.mytodoapp.controller;

import com.example.mytodoapp.config.JwtUtil;
import com.example.mytodoapp.dto.AuthRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final UserDetailsService userDetailsService;
	private final JwtUtil jwtUtil;

	public AuthController(UserDetailsService userDetailsService, JwtUtil jwtUtil) {
		this.userDetailsService = userDetailsService;
		this.jwtUtil = jwtUtil;
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
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return "Logout Successful";
	}
}
