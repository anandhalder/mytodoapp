package com.example.mytodoapp.controller;

import com.example.mytodoapp.dto.AuthRequest;
import com.example.mytodoapp.dto.RegisterUserRequest;
import com.example.mytodoapp.response.ErrorResponse;
import com.example.mytodoapp.response.SuccessResponse;
import com.example.mytodoapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final UserService userService;

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody AuthRequest authRequest) {

		// Validating the user inputs.
		if (authRequest.getUsername() == null ||
						authRequest.getUsername().isEmpty() ||
						authRequest.getPassword() == null ||
						authRequest.getPassword().isEmpty()) {
			return ResponseEntity
							.badRequest()
							.body(ErrorResponse
											.builder()
											.status(HttpStatus.BAD_REQUEST)
											.message("Username or Password cannot be empty")
											.build());
		}

		// Creating the user.
		Long userId = userService.createUser(RegisterUserRequest
						.builder()
						.username(authRequest.getUsername())
						.password(authRequest.getPassword())
						.build());

		return ResponseEntity
						.status(HttpStatus.CREATED)
						.body(SuccessResponse
										.builder()
										.status(HttpStatus.CREATED)
										.message("User created successfully with UserID : " + userId).build());
	}
}
