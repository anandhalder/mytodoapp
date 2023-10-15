package com.example.mytodoapp.dto;

import com.example.mytodoapp.Utils.Pair;
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

	private List<Task> tasks;
	private boolean isValid;
	private List<Task> invalidTasks;
	private List<Pair> validationsResult;
	private User user;
}
