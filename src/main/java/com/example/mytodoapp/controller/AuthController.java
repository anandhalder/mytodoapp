package com.example.mytodoapp.controller;

import com.example.mytodoapp.config.JwtUtil;
import com.example.mytodoapp.dto.AuthRequest;
import com.example.mytodoapp.dto.RegisterUserRequest;
import com.example.mytodoapp.response.ErrorResponse;
import com.example.mytodoapp.response.SuccessAuthResponse;
import com.example.mytodoapp.response.SuccessResponse;
import com.example.mytodoapp.services.AuthService;
import com.example.mytodoapp.services.TokenBlacklistService;
import com.example.mytodoapp.services.Impl.TokenBlacklistServiceImpl;
import com.example.mytodoapp.services.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final JwtUtil jwtUtil;
	private final TokenBlacklistServiceImpl tokenBlacklistServiceImpl;
	private final TokenBlacklistService tokenBlacklistService;
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	private final AuthService authService;

	@GetMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody AuthRequest authRequest, @RequestHeader("Authorization") String authHeader) {

		String token = authHeader.replace("Bearer ", "");

		System.out.println(token);

		if (!token.isEmpty()) {
			// Parse the token
			Claims claims = jwtUtil.parseToken(token);
			// Check if the token contains same user details as entered.
			if (claims.getSubject().equals(authRequest.getUsername()) && jwtUtil.validateToken(token)) {
				return ResponseEntity
								.status(HttpStatus.OK)
								.body(SuccessAuthResponse
												.builder()
												.token(token)
												.message("User Already logged in !")
												.build());
			} else {
				return ResponseEntity
								.status(HttpStatus.BAD_REQUEST)
								.body(ErrorResponse
												.builder()
												.status(HttpStatus.BAD_REQUEST)
												.message("Invalid Token")
												.build());
			}
		} else {
			String newToken = authService.loginService(authRequest);
			return ResponseEntity
							.status(HttpStatus.OK)
							.body(SuccessAuthResponse
											.builder()
											.token(newToken)
											.message("Login Successful")
											.build());
		}
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

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody AuthRequest authRequest) {

		// Validating the user inputs.
		if (authRequest.getUsername() == null || authRequest.getUsername().isEmpty() || authRequest.getPassword() == null || authRequest.getPassword().isEmpty()) {
			return ResponseEntity
							.badRequest()
							.body(ErrorResponse
											.builder()
											.status(HttpStatus.BAD_REQUEST)
											.message("Username or Password cannot be empty")
											.build());
		}

		// Creating the user.
		userService.createUser(RegisterUserRequest
						.builder()
						.username(authRequest.getUsername())
						.password(passwordEncoder.encode(authRequest.getPassword()))
						.build());

		return ResponseEntity
						.status(HttpStatus.CREATED)
						.body(SuccessResponse
										.builder()
										.status(HttpStatus.CREATED)
										.message("User created successfully with UserName : " + authRequest.getUsername()).build());
	}
}
