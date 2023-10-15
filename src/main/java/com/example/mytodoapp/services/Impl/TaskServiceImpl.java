package com.example.mytodoapp.services.Impl;

import com.example.mytodoapp.dto.TaskRequest;
import com.example.mytodoapp.model.Task;
import com.example.mytodoapp.repository.TaskRepository;
import com.example.mytodoapp.services.TaskService;
import com.example.mytodoapp.validation.TaskValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

	private final TaskRepository taskRepository;
	private final TaskValidationService taskValidationService;

	@Override
	public Optional<Long> addTasks(TaskRequest taskRequest) {
		taskValidationService.validate(taskRequest); // Task is valid if it doesn't exist for the current user.
		if (!taskRequest.isValid()) {
			return Optional.empty();
		}
		return taskRepository.saveAll(taskRequest.getTasks()).stream().map(Task::getId).to;
	}

	@Override
	public Optional<Task> getTaskById(Long task_id) {
		return taskRepository.findById(task_id);
	}

	@Override
	public List<Task> getAllTaskByUserId(Long user_id) {
		return taskRepository.findAllByUserId(user_id);
	}
}
