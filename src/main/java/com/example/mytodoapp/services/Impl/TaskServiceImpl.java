package com.example.mytodoapp.services.Impl;

import com.example.mytodoapp.model.Task;
import com.example.mytodoapp.repository.TaskRepository;
import com.example.mytodoapp.services.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

	private final TaskRepository taskRepository;

	// Constructor based Injection.
	TaskServiceImpl(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public void addTask() {
		return;
	}

	@Override
	public Task getTaskById(Long Id) {
		return null;
	}

	@Override
	public List<Task> getAllTask() {
		// Find User Id here !
		return taskRepository.findAllByUserId(123L);
	}
}
