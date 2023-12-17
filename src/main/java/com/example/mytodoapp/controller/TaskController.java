package com.example.mytodoapp.controller;

import com.example.mytodoapp.Utils.TaskUtils;
import com.example.mytodoapp.dto.TaskRequest;
import com.example.mytodoapp.model.Task;
import com.example.mytodoapp.services.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	private final TaskService taskService;
	private final TaskUtils taskUtils;

//	@GetMapping
//	public ResponseEntity<?> getAllTasks() { // Return all tasks for the current user.
//		LOGGER.info("TaskController -> getAllTasks called !");
//		TaskRequest taskRequest = taskUtils.createTaskRequest();
//		Optional<List<Task>> tasks = taskService.getAllTaskByUserId(taskRequest);
//		if (tasks.isEmpty()) {
//			return ResponseEntity.ok().body(SuccessResponse.builder().message("No Tasks found.").build());
//		}
//		return ResponseEntity.ok().body(tasks);
//	}
//
//	@GetMapping("/{taskId}")
//	public ResponseEntity<?> getTaskById(@PathVariable Long taskId) { // Return the Task with the given id.
//		TaskRequest taskRequest = taskUtils.createTaskRequest(taskId);
//		Optional<Task> task = taskService.getTaskById(taskRequest);
//		if (task.isEmpty()) {
//			return ResponseEntity.ok("Task not found with ID :" + taskId);
//		}
//		return ResponseEntity.ok(task.get());
//	}
//
//	@DeleteMapping("/{taskId}")
//	public ResponseEntity<?> deleteTaskById(@PathVariable Long taskId) { // Delete the Task with the given id.
//		TaskRequest taskRequest = taskUtils.createTaskRequest(taskId);
//		int deletedCount = taskService.deleteTaskByTaskId(taskRequest);
//		if (deletedCount != 1) {
//			return ResponseEntity.ok("Task not found with ID :" + taskId);
//		}
//		return ResponseEntity.ok().body("Task Deleted Successfully !");
//	}

	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody List<Task> tasks) { // Add a new Task for a Current User.
		if (tasks.isEmpty()) {
			return ResponseEntity.ok("Input is empty !");
		}
		TaskRequest taskRequest = taskUtils.createTaskRequest(tasks);
		Optional<List<Long>> tasks_id = taskService.addTasks(taskRequest);
		if (tasks_id.isEmpty()) {
			return ResponseEntity.badRequest().body(taskRequest.getInValidTasks());
		}
		return ResponseEntity.ok().body("Task created successfully with Task_ids : " + tasks_id.get().stream().map(String::valueOf).collect(Collectors.joining(", ")));
	}

//	// TODO: Fixed this code think about more better way to handle exceptions !
//	@PutMapping("/{taskId}")
//	public ResponseEntity<?> updateTask(@PathVariable Long taskId, @RequestBody Task updatedTaskDetails) {
//		TaskRequest taskRequest = taskUtils.createTaskRequest(taskId);
//		// Check if the Task Exists in DB.
//		Optional<Task> existingTask = taskService.getTaskById(taskRequest);
//		if (existingTask.isPresent()) {
//			return ResponseEntity.ok().body("Task not found with Task ID :" + taskId);
//		}
//		taskRequest.setTasks(new ArrayList<>() {{
//			add(existingTask.get());
//		}});
//		Task updatedTask = taskService.updateTask(taskRequest);
//		if (updatedTask != null) {
//			return ResponseEntity.ok().body("Task is updated :" + updatedTask);
//		}
//		return ResponseEntity.internalServerError().build();
//	}
}
