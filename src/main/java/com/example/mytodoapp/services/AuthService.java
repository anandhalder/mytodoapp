package com.example.mytodoapp.services;

import com.example.mytodoapp.config.JwtUtil;
import com.example.mytodoapp.dto.AuthRequest;
import com.example.mytodoapp.exceptions.AuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserDetailsService userDetailsService;
	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;

	public String loginService(AuthRequest authRequest) {

		UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

		// If User found. Then check the password
		Authentication auth = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
		);

		if (auth.isAuthenticated()){
			return jwtUtil.generateToken(userDetails);
		}

		throw new AuthenticationException("Invalid Username or Password");
	}

}
