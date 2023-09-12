package com.example.mytodoapp.controller;

import com.example.mytodoapp.model.Task;
import com.example.mytodoapp.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/tasks")
public class TaskController {

	private final TaskService taskService;

	// Constructor based Injection.
	TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	@GetMapping
	public List<Task> getAllTasks() {
		return taskService.getAllTask();
	}

	@GetMapping("/{id}")
	public Task getTaskById(Long id) {
		return null;
	}

	@DeleteMapping("/{id}")
	public void deleteTaskById(Long id) {
	}

	@PutMapping
	public Task addTask(@RequestBody Task task) {
		return new Task();
	}
}
