package com.example.mytodoapp.validation;

import com.example.mytodoapp.dto.TaskRequest;

public interface TaskValidationService {

	void validate(TaskRequest taskRequest);
}
