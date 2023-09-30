package com.example.mytodoapp.services.Impl;

import com.example.mytodoapp.repository.RoleRepository;
import com.example.mytodoapp.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

	private final RoleRepository roleRepository;

}