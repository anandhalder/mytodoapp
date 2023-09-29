package com.example.mytodoapp.services.Impl;

import com.example.mytodoapp.model.Role;
import com.example.mytodoapp.repository.RoleRepository;
import com.example.mytodoapp.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

	private final RoleRepository roleRepository;

	public Collection<? extends GrantedAuthority> getAllAuthorities(Long userId) {

		Optional<List<Role>> roles = roleRepository.getAllById(userId);

		if (roles.isPresent() && !roles.get().isEmpty()) {
			Set<? extends GrantedAuthority> authorities = roles.get().stream().collect(Collectors.toSet());
		}
		return
	}
}