package com.example.mytodoapp.Utils;

import com.example.mytodoapp.exceptions.ApiAuthenticationException;
import com.example.mytodoapp.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserUtils {

	public Long getCurrentUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.isAuthenticated()) {
			User currentUser = (User) authentication.getPrincipal();
			return currentUser.getId();
		}

		throw new ApiAuthenticationException("User is not authenticated !");
	}

	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.isAuthenticated()) {
			return (User) authentication.getPrincipal();
		}

		throw new ApiAuthenticationException("User is not authenticated !");
	}
}
