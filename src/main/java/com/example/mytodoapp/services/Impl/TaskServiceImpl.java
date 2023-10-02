package com.example.mytodoapp.services.Impl;

import com.example.mytodoapp.model.Task;
import com.example.mytodoapp.repository.TaskRepository;
import com.example.mytodoapp.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

	private final TaskRepository taskRepository;

	@Override
	public Long addTask(Task newTask) {
		return taskRepository.save(newTask).getId();
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
