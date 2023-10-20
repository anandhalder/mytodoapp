package com.example.mytodoapp.controller;

import com.example.mytodoapp.Utils.TaskUtils;
import com.example.mytodoapp.dto.TaskRequest;
import com.example.mytodoapp.model.Task;
import com.example.mytodoapp.response.SuccessResponse;
import com.example.mytodoapp.services.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	public ResponseEntity<?> getAllTasks() { // Return all tasks for the current user.
		TaskRequest taskRequest = taskUtils.createTaskRequest();
		Optional<List<Task>> tasks = taskService.getAllTaskByUserId(taskRequest);
		if (tasks.isEmpty()) {
			return ResponseEntity.ok().body(SuccessResponse.builder().message("No Tasks found.").build());
		}
		return ResponseEntity.ok().body(tasks);
	}

	@GetMapping("/{taskId}")
	public ResponseEntity<?> getTaskById(@PathVariable Long taskId) { // Return the Task with the given id.
		TaskRequest taskRequest = taskUtils.createTaskRequest(taskId);
		Optional<Task> task = taskService.getTaskById(taskRequest);
		if (task.isEmpty()) {
			return ResponseEntity.ok("Task not found with ID :" + taskId);
		}
		return ResponseEntity.ok(task.get());
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
