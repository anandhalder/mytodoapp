package com.example.mytodoapp.controller;

import com.example.mytodoapp.model.User;
import com.example.mytodoapp.response.SuccessResponse;
import com.example.mytodoapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

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

	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Long id) {

		User user = userService.getUserById(id);

		return ResponseEntity
						.ok()
						.body(SuccessResponse
										.builder()
										.message("User found with Id: " + user.getId())
										.data(user)
										.build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable Long id) {

		userService.deleteUserById(id);

		return ResponseEntity
						.ok()
						.body(SuccessResponse
										.builder()
										.message("User deleted successfully with Id: " + id)
										.build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateUserById(@PathVariable Long id,
																					@RequestBody User user) {

		User updatedUser = userService.updateUserById(id, user);

		return ResponseEntity
						.ok()
						.body(SuccessResponse
										.builder()
										.message("User updated successfully with Id: " + id)
										.data(updatedUser)
										.build());
	}

	@GetMapping
	public ResponseEntity<SuccessResponse<?>> getAllUsers(@RequestParam(defaultValue = "0") int page,
																												@RequestParam(defaultValue = "10") int size,
																												@RequestParam(defaultValue = "username, asc") String[] sort) {

		Page<User> users = userService.getAllUsers(createPageable(page, size, sort));

		return ResponseEntity
						.ok()
						.body(SuccessResponse
										.builder()
										.message("All Users")
										.data(users)
										.build());
	}
}
