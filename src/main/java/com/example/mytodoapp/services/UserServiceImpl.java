package com.example.mytodoapp.services;

import com.example.mytodoapp.Utils.ObjectUpdateUtility;
import com.example.mytodoapp.dto.RegisterUserRequest;
import com.example.mytodoapp.exceptions.ResourceAlreadyExistsException;
import com.example.mytodoapp.exceptions.ResourceNotFoundException;
import com.example.mytodoapp.model.Task;
import com.example.mytodoapp.model.User;
import com.example.mytodoapp.model.UserPassword;
import com.example.mytodoapp.repository.TaskRepository;
import com.example.mytodoapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final TaskRepository taskRepository;
	private final UserPasswordService userPasswordService;

	public User createUser(RegisterUserRequest newUser) {

		if (userRepository.existsByUsername(newUser.getUsername())) {
			throw new ResourceAlreadyExistsException("User already exists with username " + newUser.getUsername());
		}

		    User userCreated = userRepository.save(User.builder().username(newUser.getUsername()).build());

		    if (!userPasswordService.saveUserPassword(UserPassword.builder().userId(userCreated.getId()).password(newUser.getPassword()).build())) {
					// Rollback the changes if the password is not saved
					// Throwing exception will trigger the rollback
					throw new RuntimeException("Error while saving the password");
				}

				return userCreated;
	}

	public User getUserById(Long id) {

		Optional<User> user = userRepository.findById(id);

		if (user.isPresent()) {
			return user.get();
		} else {
			throw new ResourceNotFoundException("User not found with id: " + id);
		}
	}

	@Override
	public User getUserByUsername(String username) {

		Optional<User> existingUser = userRepository.findByUsername(username);

		if (existingUser.isEmpty()) {
			throw new ResourceNotFoundException("User not found with username: " + username);
		}

		return existingUser.get();
	}

	@Override
	public void deleteUserById(Long id) throws ResourceNotFoundException {

		Optional<User> user = userRepository.findById(id);

		if (user.isPresent()) {
			userRepository.deleteById(id);
		} else {
			throw new ResourceNotFoundException("User not found with id: " + id);
		}
	}

	@Override
	public User updateUserById(Long id, User user) {

		Optional<User> existingUser = userRepository.findById(id);

		if (existingUser.isEmpty()) {
			throw new ResourceNotFoundException("User not found with id: " + id);
		}

		if (userRepository.existsByUsername(user.getUsername())) {
			throw new ResourceAlreadyExistsException("User already exists with username " + user.getUsername());
		}

		// Updating the UserFields.
		existingUser.get().setUsername(user.getUsername());

		return userRepository.save(existingUser.get());
	}

	@Override
	public Page<Task> getAllTasksByUserId(Long id, Pageable pageable) {

		Optional<User> existingUser = userRepository.findById(id);

		if (existingUser.isEmpty()) {
			throw new ResourceNotFoundException("User not found with id: " + id);
		}

		return taskRepository.findByUserId(id, pageable);
	}

	@Override
	public void deleteTaskById(Long userid, Long taskid) {

		Optional<User> existingUser = userRepository.findById(userid);

		if (existingUser.isEmpty()) {
			throw new ResourceNotFoundException("User not found with id: " + userid);
		}

		Optional<Task> existingTask = taskRepository.findById(taskid);

		// If the Task I'd and user I'd don't match, throw an exception.
		if (existingTask.isEmpty() || !Objects.equals(existingTask.get().getUser().getId(), userid)) {
			throw new ResourceNotFoundException("Task not found with id: " + taskid);
		}

		taskRepository.deleteById(taskid);
	}


	@Override
	public Page<User> getAllUsers(Pageable pageable) {

		return userRepository.findAll(pageable);
	}

	@Override
	public Task createTask(Long userid, Task task) {

		Optional<User> existingUser = userRepository.findById(userid);

		if (existingUser.isEmpty()) {
			throw new ResourceNotFoundException("User not found with id: " + userid);
		}

		task.setUser(existingUser.get());

		return taskRepository.save(task);
	}

	@Override
	public Task getTaskById(Long userid, Long taskid) {

		Optional<User> existingUser = userRepository.findById(userid);

		if (existingUser.isEmpty()) {
			throw new ResourceNotFoundException("User not found with id: " + userid);
		}

		Optional<Task> existingTask = taskRepository.findById(taskid);

		if (existingTask.isEmpty() || !Objects.equals(existingTask.get().getUser().getId(), userid)) {
			throw new ResourceNotFoundException("Task not found with id: " + taskid);
		}

		return existingTask.get();
	}

	@Override
	public Task updateTaskById(Long userid, Long taskid, Task task) {

		Optional<User> existingUser = userRepository.findById(userid);

		if (existingUser.isEmpty()) {
			throw new ResourceNotFoundException("User not found with id: " + userid);
		}

		Optional<Task> existingTask = taskRepository.findByIdAndUserId(taskid, userid);

		if (existingTask.isEmpty()) {
			throw new ResourceNotFoundException("Task not found with id: " + taskid);
		}

		System.out.println(existingTask.get());
		// Updating the TaskFields.
		ObjectUpdateUtility.mergeObjects(task, existingTask.get());
		System.out.println(existingTask.get());

		return taskRepository.save(existingTask.get());
	}

	@Override
	public Task updateTaskAssignment(Long userid, Long taskid, Long newuserid) {

		Optional<User> existingUser = userRepository.findById(newuserid);

		if (existingUser.isEmpty()) {
			throw new ResourceNotFoundException("User not found with Id " + newuserid);
		}

		Optional<Task> existingTask = taskRepository.findByIdAndUserId(taskid, userid);

		if (existingTask.isEmpty()) {
			throw new ResourceNotFoundException("Task not found with Id " + taskid);
		}

		existingTask.get().setUser(existingUser.get());

		return taskRepository.save(existingTask.get());
	}
}

