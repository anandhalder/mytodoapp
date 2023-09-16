package com.example.mytodoapp.security;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class ApiAuthenticationToken extends AbstractAuthenticationToken {

	private final Long userId;
	@Getter
	private final String username;

	@Builder
	public ApiAuthenticationToken(Long userId, String username, List<GrantedAuthority> authorities, boolean authenticated) {
		super(authorities);
		super.setAuthenticated(authenticated);
		this.userId = userId;
		this.username = username;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return userId;
	}

	public Collection<GrantedAuthority> getAuthorities() {
		return super.getAuthorities();
	}
}
