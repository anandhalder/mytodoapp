package com.example.mytodoapp.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface RoleService {

	Collection<? extends GrantedAuthority> getAllAuthorities(Long id);
}
