package com.example.mytodoapp.security;

import com.example.mytodoapp.model.User;
import com.example.mytodoapp.services.UserPasswordService;
import com.example.mytodoapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiAuthenticationProvider implements AuthenticationProvider {

	private final UserService userService;
	private UserPasswordService userPasswordService;

	@Override
	public ApiAuthenticationToken authenticate(Authentication authentication) {

		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		User user = userService.getUserByUsername(username);

		if (user != null) {
			Long userId = user.getId();
			if (userPasswordService.checkCredentials(userId, password)) {
				return ApiAuthenticationToken.builder()
								.userId(userId)
								.username(username)
								.authenticated(true)
								.build();
			}
		}

		return ApiAuthenticationToken.builder()
						.authenticated(false)
						.build();
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return false;
	}
}
