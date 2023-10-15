package com.example.mytodoapp.validation.Impl;

import com.example.mytodoapp.Utils.Pair;
import com.example.mytodoapp.dto.TaskRequest;
import com.example.mytodoapp.model.Task;
import com.example.mytodoapp.repository.TaskRepository;
import com.example.mytodoapp.validation.TaskValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskValidationsServiceImpl implements TaskValidationService {

	private final TaskRepository taskRepository;

	public void validate(TaskRequest taskRequest) {
		doTaskValidation(taskRequest);
		taskRequest.setValid(true);
	}

	public void doTaskValidation(TaskRequest taskRequest) {
		List<Task> tasks = taskRequest.getTasks();

		for (int i = 0; i < tasks.size(); i++) {
			checkTaskDescription(i, tasks.get(i), taskRequest);
		}
	}

	public void checkTaskDescription(int id, Task task, TaskRequest taskRequest) {

		if (task.getDescription() != null) {
			task.setDescription(sanitize(task.getDescription()));
		} else {
			taskRequest.getInvalidTasks().set(id, task);
			if (taskRequest.getValidationsResult().isEmpty()) {
				taskRequest.setValidationsResult(new ArrayList<>() {{
					add(new Pair(id, "Task Description cannot be empty."));
				}});
			}
			taskRequest.setValid(false);
		}
	}

	public String sanitize(String input) {
		// Define a regular expression pattern to allow only alphanumeric characters and spaces
		String sanitized = input.replaceAll("[^a-zA-Z0-9\\s]", "");
		return sanitized.trim();
	}
}
