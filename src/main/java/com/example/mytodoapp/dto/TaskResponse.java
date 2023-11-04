package com.example.mytodoapp.dto;

import com.example.mytodoapp.model.Task;
import com.example.mytodoapp.model.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class TaskResponse {

	User user;
	List<Task> tasks;
	List<Task> inValidTasks;
}
