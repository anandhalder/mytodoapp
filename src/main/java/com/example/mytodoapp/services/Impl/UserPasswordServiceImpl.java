package com.example.mytodoapp.services.Impl;

import com.example.mytodoapp.model.UserPassword;
import com.example.mytodoapp.repository.UserPasswordRepository;
import com.example.mytodoapp.services.UserPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserPasswordServiceImpl implements UserPasswordService {

	private final UserPasswordRepository userPasswordRepository;

	@Override
	public boolean saveUserPassword(UserPassword userPassword) {
		return userPasswordRepository.save(userPassword).getUserId() != null;
	}
}
