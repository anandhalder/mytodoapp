package com.example.mytodoapp.services;


import com.example.mytodoapp.model.UserPassword;

public interface UserPasswordService {

	boolean checkCredentials(Long userid, String password);

	boolean saveUserPassword(UserPassword userPassword);
}
