package com.example.mytodoapp.controller;

import com.example.mytodoapp.model.Task;
import com.example.mytodoapp.model.User;
import com.example.mytodoapp.response.SuccessResponse;
import com.example.mytodoapp.services.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/public")
	public String getWelcomeMessage() {
		return "Hello Public";
	}

	public Pageable createPageable(int page, int size, String[] sort) {

		// Creating the Sort Class Object
		List<Sort.Order> sort_list = new ArrayList<>();

		if (sort.length != 0 && sort.length % 2 == 0) {
			sort_list = new ArrayList<>();

			// Sample Url Looks Like http://localhost:8080/users?page=2&size=2&sort=id,desc,username,asc
			for (int i = 0; i < sort.length; i += 2) {
				sort_list.add(new Sort.Order(Sort.Direction.fromString(sort[i + 1].toUpperCase()), sort[i]));
			}
		}

		Sort sortable = Sort.by(sort_list);

		return PageRequest.of(page, size, sortable);
	}

	@PostMapping("/users")
	public ResponseEntity<?> createUser(@Valid @RequestBody User newUser) {

		User createdUser = userService.createUser(newUser);

		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(createdUser.getId())
						.toUri();

		return ResponseEntity
						.created(location)
						.body(SuccessResponse
										.builder()
										.status(HttpStatus.CREATED)
										.message("User successfully created with Id: " + createdUser.getId())
										.data(createdUser)
										.build());
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Long id) {

		User user = userService.getUserById(id);

		return ResponseEntity
						.ok()
						.body(SuccessResponse
										.builder()
										.status(HttpStatus.FOUND)
										.message("User found with Id: " + user.getId())
										.data(user)
										.build());
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable Long id) {

		userService.deleteUserById(id);

		return ResponseEntity
						.ok()
						.body(SuccessResponse
										.builder()
										.status(HttpStatus.NO_CONTENT)
										.message("User deleted successfully with Id: " + id)
										.build());
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<?> updateUserById(@PathVariable Long id,
																					@RequestBody User user) {

		User updatedUser = userService.updateUserById(id, user);

		return ResponseEntity
						.ok()
						.body(SuccessResponse
										.builder()
										.status(HttpStatus.OK)
										.message("User updated successfully with Id: " + id)
										.data(updatedUser)
										.build());
	}

	@GetMapping("users")
	public ResponseEntity<SuccessResponse<?>> getAllUsers(@RequestParam(defaultValue = "0") int page,
																												@RequestParam(defaultValue = "10") int size,
																												@RequestParam(defaultValue = "username, asc") String[] sort) {

		Page<User> users = userService.getAllUsers(createPageable(page, size, sort));

		return ResponseEntity
						.ok()
						.body(SuccessResponse
										.builder()
										.status(HttpStatus.OK)
										.message("All Users")
										.data(users)
										.build());
	}

	// This will sort the tasks by dueDate in ascending order.
	@GetMapping("users/{id}/tasks")
	public ResponseEntity<SuccessResponse<?>> getAllTasksByUserId(@PathVariable long id,
																																@RequestParam(defaultValue = "0") int page,
																																@RequestParam(defaultValue = "10") int size,
																																@RequestParam(defaultValue = "dueDate, asc") String[] sort) {

		Pageable pageable = createPageable(page, size, sort);

		Page<Task> tasks = userService.getAllTasksByUserId(id, pageable);

		return ResponseEntity
						.ok()
						.body(SuccessResponse
										.builder()
										.status(HttpStatus.OK)
										.message("All Task Found for User Id :" + id)
										.data(tasks)
										.build());
	}

	@GetMapping("users/{userid}/tasks/{taskid}")
	public ResponseEntity<SuccessResponse<?>> getTaskById(@PathVariable long userid,
																												@PathVariable long taskid) {

		Task task = userService.getTaskById(userid, taskid);

		return ResponseEntity
						.ok()
						.body(SuccessResponse
										.builder()
										.status(HttpStatus.OK)
										.message("Task found with Id: " + task.getId() + " with User id " + userid)
										.data(task)
										.build());
	}

	@DeleteMapping("users/{userid}/tasks/{taskid}")
	public ResponseEntity<SuccessResponse<?>> deleteTask(@PathVariable long userid,
																											 @PathVariable long taskid) {

		userService.deleteTaskById(userid, taskid);

		return ResponseEntity
						.ok()
						.body(SuccessResponse
										.builder()
										.status(HttpStatus.NO_CONTENT)
										.message("Task deleted successfully with Id: " + taskid + " with User id " + userid)
										.build());
	}

	@PostMapping("users/{userid}/tasks")
	public ResponseEntity<SuccessResponse<?>> createTask(@PathVariable Long userid,
																											 @RequestBody Task task) {

		Task createdTask = userService.createTask(userid, task);

		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(createdTask.getId())
						.toUri();

		return ResponseEntity
						.created(location)
						.body(SuccessResponse
										.builder()
										.status(HttpStatus.CREATED)
										.message("Task successfully created with Id: " + createdTask.getId())
										.data(createdTask)
										.build());
	}

	@PutMapping("users/{userid}/tasks/{taskid}")
	public ResponseEntity<SuccessResponse<?>> updateTask(@PathVariable Long userid,
																											 @PathVariable Long taskid,
																											 @RequestBody Task task) {

		Task updatedTask = userService.updateTaskById(userid, taskid, task);

		return ResponseEntity
						.ok()
						.body(SuccessResponse
										.builder()
										.status(HttpStatus.OK)
										.message("Task updated successfully with Id: " + taskid)
										.data(updatedTask)
										.build());
	}

	@PutMapping("users/{userid}/tasks/{taskid}/{newuserid}")
	public ResponseEntity<SuccessResponse<?>> updateTaskAssignment(@PathVariable Long userid,
																																 @PathVariable Long taskid,
																																 @PathVariable Long newuserid) {

		Task updatedTask = userService.updateTaskAssignment(userid, taskid, newuserid);

		return ResponseEntity
						.ok()
						.body(SuccessResponse
										.builder()
										.status(HttpStatus.OK)
										.message("Task is updated with the new User as an assignment " + newuserid)
										.data(updatedTask).build());
	}
}
