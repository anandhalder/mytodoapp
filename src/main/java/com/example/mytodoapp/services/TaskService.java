package com.example.mytodoapp.services;

import com.example.mytodoapp.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {

	public void addTask();

	public Task getTaskById(Long Id);

	public List<Task> getAllTask();
}
