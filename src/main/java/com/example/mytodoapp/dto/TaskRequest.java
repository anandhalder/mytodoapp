package com.example.mytodoapp.dto;

import com.example.mytodoapp.model.Task;
import com.example.mytodoapp.model.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class TaskRequest {

	private Long taskId;
	private List<Task> tasks;
	private User user;
}
