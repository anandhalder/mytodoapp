package com.example.mytodoapp.controller;

import com.example.mytodoapp.Utils.CurrentUserUtils;
import com.example.mytodoapp.model.Task;
import com.example.mytodoapp.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/tasks")
@RequiredArgsConstructor
public class TaskController {

	private final TaskService taskService;
	private final CurrentUserUtils currentUserUtils;

	@GetMapping
	public List<Task> getAllTasks() { // Return all tasks for the current user.
		Long user_id = currentUserUtils.getCurrentUserId();
		return taskService.getAllTaskByUserId(user_id);
	}

	@GetMapping("/{id}")
	public Task getTaskById(Long id) { // Return the Task with the given id.
		return null;
	}

	@DeleteMapping("/{id}")
	public void deleteTaskById(Long id) { // Delete the Task with the given id.
	}

	@PutMapping
	public Task addTask(@RequestBody Task task) { // Add a new Task for a Current User.
		return new Task();
	}
}
