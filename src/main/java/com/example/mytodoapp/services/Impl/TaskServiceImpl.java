package com.example.mytodoapp.services.Impl;

import com.example.mytodoapp.model.Task;
import com.example.mytodoapp.repository.TaskRepository;
import com.example.mytodoapp.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

	private final TaskRepository taskRepository;

	@Override
	public void addTask() {
	}

	@Override
	public Task getTaskById(Long task_id) {
		return null;
	}

	@Override
	public List<Task> getAllTaskByUserId(Long user_id) {
		return taskRepository.findAllByUserId(user_id);
	}
}
