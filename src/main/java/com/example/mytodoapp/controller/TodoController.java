package com.example.mytodoapp.controller;

import com.example.mytodoapp.model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/tasks")
public class TodoController {

	@GetMapping("/")
	public List<Task> getAllTasks() {
		return null;
	}

	@GetMapping("/{id}")
	public Task getTaskById(Long id) {
		return null;
	}

	@DeleteMapping("/{id}")
	public void deleteTaskById(Long id) {
	}

	@PutMapping("/")
	public Task addTask(@RequestBody Task task) {
		return new Task();
	}
}
