package com.example.mytodoapp.services;

import com.example.mytodoapp.exceptions.ResourceNotFoundException;
import com.example.mytodoapp.model.UserPassword;
import com.example.mytodoapp.repository.UserPasswordRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPasswordServiceImpl implements UserPasswordService {

	private final UserPasswordRepository userPasswordRepository;

	public UserPasswordServiceImpl(UserPasswordRepository userPasswordRepository) {
		this.userPasswordRepository = userPasswordRepository;
	}

	@Override
	public String getPasswordByUserId(Long userid) throws Exception {

		Optional<UserPassword> userPassword = userPasswordRepository.findById(userid);

		if (userPassword.isEmpty()) {
			throw new ResourceNotFoundException("User not found with id: " + userid);
		}

		return userPassword.get().getPassword();
	}

	@Override
	public boolean saveUserPassword(UserPassword userPassword) {
		return userPasswordRepository.save(userPassword).getUserId() != null;
	}
}
