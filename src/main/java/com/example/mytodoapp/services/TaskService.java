package com.example.mytodoapp.services;

import com.example.mytodoapp.dto.TaskRequest;
import com.example.mytodoapp.dto.TaskResponse;

public interface TaskService {

	TaskResponse addTasks(TaskRequest taskRequest);

	TaskResponse getTaskById(TaskRequest taskRequest);

	TaskResponse getAllTaskByUserId(TaskRequest taskRequest);

	TaskResponse deleteTaskByTaskId(TaskRequest taskRequest);

	TaskResponse updateTask(TaskRequest taskRequest);
}
