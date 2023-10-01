package com.example.mytodoapp.services;

import com.example.mytodoapp.model.Task;

import java.util.List;

public interface TaskService {

	void addTask();

	Task getTaskById(Long Id);

	List<Task> getAllTaskByUserId(Long UserId);
}
