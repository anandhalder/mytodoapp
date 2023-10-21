package com.example.mytodoapp.services.Impl;

import com.example.mytodoapp.dto.TaskRequest;
import com.example.mytodoapp.model.Task;
import com.example.mytodoapp.repository.TaskRepository;
import com.example.mytodoapp.services.TaskService;
import com.example.mytodoapp.validation.TaskValidationService;
import jakarta.transaction.Transactional;
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
	public Optional<List<Long>> addTasks(TaskRequest taskRequest) {
		taskValidationService.validate(taskRequest); // Task is valid if it doesn't exist for the current user.
		if (!taskRequest.isValid()) {
			return Optional.empty();
		}
		return Optional.of(taskRepository.saveAll(taskRequest.getTasks()).stream().map(Task::getId).toList());
	}

	@Override
	public Optional<Task> getTaskById(TaskRequest taskRequest) {
		return taskRepository.findByIdAndUserId(taskRequest.getTaskId(), taskRequest.getUser().getId());
	}

	@Override
	public Optional<List<Task>> getAllTaskByUserId(TaskRequest taskRequest) {
		return Optional.ofNullable(taskRepository.findAllByUserId(taskRequest.getUser().getId()));
	}

	@Transactional
	@Override
	public int deleteTaskByTaskId(TaskRequest taskRequest) {
		return taskRepository.deleteByIdAndUserId(taskRequest.getTaskId(), taskRequest.getUser().getId());
	}
}
