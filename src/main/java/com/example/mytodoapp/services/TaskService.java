package com.example.mytodoapp.services;

import com.example.mytodoapp.dto.TaskRequest;
import com.example.mytodoapp.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

	Optional<List<Long>> addTasks(TaskRequest taskRequest);

	Optional<Task> getTaskById(TaskRequest taskRequest);

	Optional<List<Task>> getAllTaskByUserId(TaskRequest taskRequest);
}
