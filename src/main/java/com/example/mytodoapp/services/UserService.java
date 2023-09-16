package com.example.mytodoapp.services;

import com.example.mytodoapp.dto.RegisterUserRequest;
import com.example.mytodoapp.model.Task;
import com.example.mytodoapp.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

	void createUser(RegisterUserRequest newUser);

	User getUserById(Long id);

	User getUserByUsername(String username);

	void deleteUserById(Long id);

	User updateUserById(Long id, User user);

	Page<User> getAllUsers(Pageable pageable);

	Page<Task> getAllTasksByUserId(Long id, Pageable pageable);

	void deleteTaskById(Long userid, Long taskid);

	Task createTask(Long userid, Task task);

	Task getTaskById(Long userid, Long taskid);

	Task updateTaskById(Long userid, Long taskid, Task task);

	Task updateTaskAssignment(Long userid, Long taskid, Long newuserid);
}
