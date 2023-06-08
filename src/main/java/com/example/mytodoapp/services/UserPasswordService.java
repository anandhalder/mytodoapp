package com.example.mytodoapp.services;


import com.example.mytodoapp.model.UserPassword;

public interface UserPasswordService {

	String getPasswordByUserId(Long userid) throws Exception;
	boolean saveUserPassword(UserPassword userPassword);
}
