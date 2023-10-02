package com.example.mytodoapp.validation;

import com.example.mytodoapp.model.Task;
import com.example.mytodoapp.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskValidationsService {

	private final TaskService taskService;

	public boolean isTaskValid(Task task) {
		task.setDescription(sanitize(task.getDescription()));
		// Check if the same task exists for the current user.
		return taskService.getAllTaskByUserId(task.getUser().getId()).stream()
						.noneMatch(t -> t.getDescription().equals(task.getDescription()));
	}

	public String sanitize(String input) {
		// Define a regular expression pattern to allow only alphanumeric characters and spaces
		String sanitized = input.replaceAll("[^a-zA-Z0-9\\s]", "");
		return sanitized.trim();
	}
}
