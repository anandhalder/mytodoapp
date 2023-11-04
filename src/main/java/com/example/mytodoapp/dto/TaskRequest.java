package com.example.mytodoapp.dto;

import com.example.mytodoapp.model.Task;
import com.example.mytodoapp.model.User;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TaskRequest {

	private Long taskId;
	private List<Task> tasks;
	private List<Task> inValidTasks;
	private User user;
}
