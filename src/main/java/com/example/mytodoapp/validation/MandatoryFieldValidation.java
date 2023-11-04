package com.example.mytodoapp.validation;

import com.example.mytodoapp.Utils.CommonUtils;
import com.example.mytodoapp.dto.TaskRequest;
import com.example.mytodoapp.dto.TaskResponse;
import com.example.mytodoapp.model.Task;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MandatoryFieldValidation {

	private final CommonUtils commonUtils;

	public void validate(TaskRequest taskRequest, TaskResponse taskResponse) {
		checkTaskDescription(taskRequest, taskResponse);
	}

	public void checkTaskDescription(TaskRequest taskRequest, TaskResponse taskResponse) { // TODO: we can add more details to validation response !
		List<Task> inValidTasks = taskRequest.
						getTasks().
						stream().
						filter(task -> task.getDescription() == null || commonUtils.sanitize(task.getDescription()).isEmpty()).
						toList();
		if (!inValidTasks.isEmpty()) {
			taskResponse.setInValidTasks(inValidTasks);
		}
	}
}
