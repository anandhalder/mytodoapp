package com.example.mytodoapp.services;


import com.example.mytodoapp.model.UserPassword;

public interface UserPasswordService {
	
	boolean saveUserPassword(UserPassword userPassword);

	boolean checkPassword(Long userId, String password);
}
