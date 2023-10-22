package com.example.mytodoapp.Utils;

import com.example.mytodoapp.dto.TaskRequest;
import com.example.mytodoapp.model.Task;
import com.example.mytodoapp.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
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

	public TaskRequest createTaskRequest(Long task_id) {
		User user = currentUserUtils.getCurrentUser();
		return TaskRequest.builder().user(user).taskId(task_id).build();
	}
	
	public void updateTaskDetails(Task existingTask, Task updatedTaskDetails) {
		if (existingTask != null && updatedTaskDetails != null) {
			Class<?> taskClass = existingTask.getClass();
			Field[] fields = taskClass.getDeclaredFields();

			for (Field field : fields) {
				field.setAccessible(true);
				try {
					Object value = field.get(updatedTaskDetails);
					if (value != null) {
						field.set(existingTask, value);
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
