package com.example.mytodoapp.validation;

import com.example.mytodoapp.Utils.CommonUtils;
import com.example.mytodoapp.dto.TaskRequest;
import com.example.mytodoapp.model.Task;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MandatoryFieldValidation {

	private final CommonUtils commonUtils;

	public void validate(TaskRequest taskRequest) {
		taskRequest.getTasks().forEach(this::checkTaskDescription);
	}

	public void checkTaskDescription(Task task) {
		if (task.getDescription() != null && !task.getDescription().isEmpty()) {
			task.setDescription(commonUtils.sanitize(task.getDescription()));
		}
	}
}
