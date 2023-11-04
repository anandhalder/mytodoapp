package com.example.mytodoapp.validation;

import com.example.mytodoapp.dto.TaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskValidationService {

	private final MandatoryFieldValidation mandatoryFieldValidation;

	public void validate(TaskRequest taskRequest) {
		mandatoryFieldValidation.validate(taskRequest);
	}
}
