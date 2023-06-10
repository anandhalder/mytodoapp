package com.example.mytodoapp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

	@NotEmpty(message = "Username cannot be empty")
	private String username;

	// TODO: Add validation for password like length, special characters, etc.
	@NotEmpty(message = "Password cannot be empty")
	private String password;
}
