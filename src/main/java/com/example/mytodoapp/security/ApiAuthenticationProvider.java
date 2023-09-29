package com.example.mytodoapp.security;

import com.example.mytodoapp.model.User;
import com.example.mytodoapp.services.UserPasswordService;
import com.example.mytodoapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class ApiAuthenticationProvider implements AuthenticationProvider {

	private final UserService userService;
	private final UserPasswordService userPasswordService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getPrincipal().toString();
		String password = authentication.getCredentials().toString();

		if (!(username.isEmpty() && password.isEmpty())) {
			User user = userService.getUserByUsername(username);
			if (userPasswordService.checkPassword(user.getId(), password)) {
				return new UsernamePasswordAuthenticationToken(user, null, getAuthorities(user.getId()));
			} else {
				return new UsernamePasswordAuthenticationToken(null, null);
			}
		}
		// TODO:Not sure if this is ok !
		throw new BadCredentialsException("Username Or Password cannot be empty !");
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Long userId) {


	}

	@Override
	public boolean supports(Class<?> authentication) {
		return false;
	}
}
