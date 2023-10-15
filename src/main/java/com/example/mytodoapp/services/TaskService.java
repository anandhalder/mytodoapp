package com.example.mytodoapp.services;

import com.example.mytodoapp.dto.TaskRequest;
import com.example.mytodoapp.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

	Optional<Long> addTasks(TaskRequest taskRequest);

	Optional<Task> getTaskById(Long Id);

	List<Task> getAllTaskByUserId(Long UserId);
}
