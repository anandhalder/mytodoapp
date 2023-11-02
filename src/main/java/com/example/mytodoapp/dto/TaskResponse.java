package com.example.mytodoapp.dto;

import com.example.mytodoapp.Utils.Pair;
import com.example.mytodoapp.model.Task;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class TaskResponse {

	List<Task> tasks;
	List<Pair> invalidTasks;
}
