package com.example.mytodoapp.controller;

import com.example.mytodoapp.Utils.TaskUtils;
import com.example.mytodoapp.dto.TaskRequest;
import com.example.mytodoapp.model.Task;
import com.example.mytodoapp.services.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

	private final TaskService taskService;
	private final TaskUtils taskUtils;

	@GetMapping
	public List<Task> getAllTasks() { // Return all tasks for the current user.
		return new ArrayList<>();
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
		Optional<List<Long>> tasks_id = taskService.addTasks(taskRequest);

		if (tasks_id.isEmpty()) {
			return ResponseEntity.badRequest().body(taskRequest.getValidationsResult());
		}
		return ResponseEntity.ok().body("Task created successfully with Task_ids : " + tasks_id.get().stream().map(String::valueOf).collect(Collectors.joining(", ")));
	}
}
