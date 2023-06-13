package com.example.mytodoapp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserService userService;
	private final UserPasswordService userPasswordService;

	@Override
	// TODO: DO Better Exception Handling.
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		List<GrantedAuthority> authorities = new ArrayList<>() {{
			add((GrantedAuthority) () -> "ROLE_USER");
		}};

		// Fetching the user from the database.
		com.example.mytodoapp.model.User existingUser = userService.getUserByUsername(username);

		try {
			// Getting the password from the database.
			String password = userPasswordService.getPasswordByUserId(existingUser.getId());
			return new User(existingUser.getUsername(), password, authorities);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
