package com.example.mytodoapp.controller;

import com.example.mytodoapp.Utils.TaskUtils;
import com.example.mytodoapp.dto.TaskRequest;
import com.example.mytodoapp.model.Task;
import com.example.mytodoapp.services.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

	private final TaskService taskService;
	private final TaskUtils taskUtils;

	@GetMapping
	public List<Task> getAllTasks() { // Return all tasks for the current user.
		return taskService.getAllTaskByUserId();
	}

	@GetMapping("/{id}")
	public Task getTaskById(Long id) { // Return the Task with the given id.
		return null;
	}

	@DeleteMapping("/{id}")
	public void deleteTaskById(Long id) { // Delete the Task with the given id.
	}

	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody List<Task> tasks) { // Add a new Task for a Current User.
		TaskRequest taskRequest = taskUtils.createTaskRequest(tasks);
		return ResponseEntity.badRequest().body("Validation Failed : ");
	}
}
