package com.example.mytodoapp.services.Impl;

import com.example.mytodoapp.Utils.TaskUtils;
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
	private final TaskUtils taskUtils;

	@Override
	public TaskResponse addTasks(TaskRequest taskRequest) {
		TaskResponse taskResponse = createTaskResponse(taskRequest);
		taskValidationService.validate(taskRequest, taskResponse);
		return taskResponse;
	}

	@Override
	public TaskResponse getTaskById(TaskRequest taskRequest) {
		TaskResponse taskResponse = createTaskResponse(taskRequest);
		taskRepository.findByIdAndUserId(taskRequest.getTaskId(), taskRequest.getUser().getId());
		return taskResponse;
	}

	@Override
	public TaskResponse getAllTaskByUserId(TaskRequest taskRequest) {
		TaskResponse taskResponse = createTaskResponse(taskRequest);
		Optional.ofNullable(taskRepository.findAllByUserId(taskRequest.getUser().getId()));
		return taskResponse;
	}

	@Transactional
	@Override
	public TaskResponse deleteTaskByTaskId(TaskRequest taskRequest) {
		TaskResponse taskResponse = createTaskResponse(taskRequest);
		taskRepository.deleteByIdAndUserId(taskRequest.getTaskId(), taskRequest.getUser().getId());
		return taskResponse;
	}

	@Override
	public TaskResponse updateTask(TaskRequest taskRequest) {
		TaskResponse taskResponse = createTaskResponse(taskRequest);
		Task existingTask = taskRequest.getTasks().get(0);
		taskUtils.updateTaskDetails(existingTask, taskRequest.getTasks().get(0));
		taskRepository.save(existingTask);
		return taskResponse;
	}

	public TaskResponse createTaskResponse(TaskRequest taskRequest) {
		return TaskResponse.builder().user(taskRequest.getUser()).build();
	}
}
