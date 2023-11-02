package com.example.mytodoapp.dto;

import com.example.mytodoapp.model.Task;
import com.example.mytodoapp.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class TaskRequest {
	
	private Long taskId;
	private List<Task> tasks;
	private List<Task> validTasks;
	private User user;
}
