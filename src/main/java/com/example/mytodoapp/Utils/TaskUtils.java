package com.example.mytodoapp.Utils;

import com.example.mytodoapp.dto.TaskRequest;
import com.example.mytodoapp.model.Task;
import com.example.mytodoapp.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskUtils {

	private final CurrentUserUtils currentUserUtils;

	public TaskRequest createTaskRequest(List<Task> tasks) {
		User user = currentUserUtils.getCurrentUser();
		tasks.forEach(task -> task.setUser(user));
		return TaskRequest.builder().user(user).tasks(tasks).build();
	}

	public TaskRequest createTaskRequest() {
		User user = currentUserUtils.getCurrentUser();
		return TaskRequest.builder().user(user).build();
	}

	public void setUserId(User logged_in_user, TaskRequest taskRequest) {
		if (taskRequest != null && logged_in_user != null) {
			taskRequest.getTasks().forEach(task -> task.setUser(logged_in_user));
		}
		throw new RuntimeException("TaskRequest or UserId cannot be Null !");
	}
}
