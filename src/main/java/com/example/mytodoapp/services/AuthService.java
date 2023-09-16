package com.example.mytodoapp.services;

import com.example.mytodoapp.config.JwtUtil;
import com.example.mytodoapp.dto.AuthRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;


//@Service TODO: Not Required for now, because for API application user will send username and password for every call !
@RequiredArgsConstructor
public class AuthService {

	private final UserDetailsService userDetailsService;
	private final JwtUtil jwtUtil;

	public String loginService(AuthRequest authRequest) {

		return "";
	}

}
